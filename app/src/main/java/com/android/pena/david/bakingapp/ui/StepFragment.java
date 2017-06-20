package com.android.pena.david.bakingapp.ui;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.pena.david.bakingapp.Model.Step;
import com.android.pena.david.bakingapp.R;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.PlaybackParameters;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.Timeline;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.mediacodec.MediaCodecRenderer;
import com.google.android.exoplayer2.mediacodec.MediaCodecUtil;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.source.hls.BuildConfig;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by david on 01/06/17.
 */

public class StepFragment extends Fragment implements ExoPlayer.EventListener, ImageView.OnClickListener {


    @BindView(R.id.exoplayer) SimpleExoPlayerView stepPlayerView;
    @BindView(R.id.video_buffering) ProgressBar video_buffering;
    @BindView(R.id.refresh_button) ImageView refresh_btn;
    @BindView(R.id.no_video) ImageView no_video_icon;
    @Nullable @BindView(R.id.next_fab) FloatingActionButton next_fab;
    @Nullable @BindView(R.id.previous_fab) FloatingActionButton previous_fab;
    @Nullable @BindView(R.id.descrition_textview) TextView descrition_textview;


    private static final String STEP_PARCELABLE = "step_parcelable";
    private static final String NUMBER_OF_STEPS = "number_of_steps";

    private SimpleExoPlayer stepPlayer;
    private Handler mainHandler;
    private Context context;
    private DataSource.Factory mediaDataSourceFactory;
    private DefaultTrackSelector trackSelector;
    private boolean autoPlay;
    private boolean hasResume;
    private long resumePosition;
    private String userAgent;
    private int num_steps;
    private static final DefaultBandwidthMeter BANDWIDTH_METER = new DefaultBandwidthMeter();

    private Step step;


    onChangeStepListener onChangeStepListener;

    public interface onChangeStepListener{
        void nextStep(int id);
        void previousStep(int id);
    }


    public StepFragment(){}

    public static StepFragment newInstance(Step step, int num_steps){
        StepFragment fragment = new StepFragment();
        Bundle args = new Bundle();
        args.putParcelable(STEP_PARCELABLE,step);
        args.putInt(NUMBER_OF_STEPS,num_steps);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkOrientation();
        context = getContext();
        if(getArguments() != null){
            step = getArguments().getParcelable(STEP_PARCELABLE);
            num_steps = getArguments().getInt(NUMBER_OF_STEPS);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d("onCreateView","onCreateView");
        View view = inflater.inflate(R.layout.step_fragment,container,false);
        ButterKnife.bind(this,view);
        resumePosition = -1;
        hasResume = false;
        userAgent = Util.getUserAgent(context,"BakingApp");
        refresh_btn.setOnClickListener(this);
        autoPlay = true;
        mediaDataSourceFactory = new DefaultDataSourceFactory(context,BANDWIDTH_METER,
                new DefaultHttpDataSourceFactory(userAgent,BANDWIDTH_METER));
        mainHandler = new Handler();
        stepPlayerView.requestFocus();

        setFabs();
        if(descrition_textview != null){
            descrition_textview.setText(step.getDescription());
        }


        return view;
    }


    @Override
    public void onStart() {
        super.onStart();
        if (Util.SDK_INT > 23) {
            initializePlayer(step.getVideoURL());
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if ((Util.SDK_INT <= 23 || stepPlayer == null)) {
            initializePlayer(step.getVideoURL());
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (Util.SDK_INT <= 23) {
            releasePlayer();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (Util.SDK_INT > 23) {
            releasePlayer();
        }
    }


    private void checkOrientation(){
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            ((RecipeActivity) getActivity()).getSupportActionBar().hide();
            getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); //Remove notification bar
        }
    }


    private void setFabs(){

        if(next_fab == null || previous_fab == null ) return;
        if(step.getId() != num_steps-1) {
            next_fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onChangeStepListener.nextStep(step.getId());
                }
            });
        }else{
            next_fab.setVisibility(View.GONE);
        }

        if(step.getId() > 0){
            previous_fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onChangeStepListener.previousStep(step.getId());
                }
            });
        }else{
            previous_fab.setVisibility(View.GONE);
        }
    }


    private void initializePlayer(String video_url){


        if(stepPlayer != null || video_url.isEmpty()){
            no_video_icon.setVisibility(View.VISIBLE);
            return;
        }

        @DefaultRenderersFactory.ExtensionRendererMode int extensionRendererMode =
                BuildConfig.FLAVOR.equals("withExtensions")
                        ? (DefaultRenderersFactory.EXTENSION_RENDERER_MODE_ON)
                        : DefaultRenderersFactory.EXTENSION_RENDERER_MODE_OFF;
        DefaultRenderersFactory renderersFactory = new DefaultRenderersFactory(context,
                null, extensionRendererMode);

        TrackSelection.Factory adaptativeTrackSelectionFactory =
                new AdaptiveTrackSelection.Factory(BANDWIDTH_METER);

        trackSelector = new DefaultTrackSelector(adaptativeTrackSelectionFactory);
        stepPlayer = ExoPlayerFactory.newSimpleInstance(renderersFactory,trackSelector);
        stepPlayer.addListener(this);
        stepPlayerView.setPlayer(stepPlayer);
        stepPlayer.setPlayWhenReady(autoPlay);
        Uri uri = Uri.parse(video_url);
        MediaSource mediaSource = new ExtractorMediaSource(uri, mediaDataSourceFactory, new DefaultExtractorsFactory(),
                mainHandler, null);

        if(hasResume){
            stepPlayer.seekTo(resumePosition);
        }
        stepPlayer.prepare(mediaSource);

    }


    private void releasePlayer(){
        if(stepPlayer != null){
            autoPlay = stepPlayer.getPlayWhenReady();
            updateResumePosition();
            stepPlayer.release();
            stepPlayer = null;
            trackSelector = null;


        }
    }

    private void updateResumePosition(){
        hasResume = true;
        resumePosition = stepPlayer.getCurrentPosition();
    }

    private void refreshVideo(){
        hasResume = false;
        resumePosition = -1;
        stepPlayer.seekTo(0);
        refresh_btn.setVisibility(View.GONE);
    }

    //Refresh Button clickListener
    @Override
    public void onClick(View v) {
        if(stepPlayer != null){
            refreshVideo();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            onChangeStepListener = (onChangeStepListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement onChangeStepListener");
        }
    }


    @Override
    public void onDetach() {
        super.onDetach();
        onChangeStepListener = null;

    }

    //----------ExoPlayer EventListeners----------
    @Override
    public void onTimelineChanged(Timeline timeline, Object manifest) {

    }

    @Override
    public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {

    }

    @Override
    public void onLoadingChanged(boolean isLoading) {

    }

    @Override
    public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
        if (playbackState == ExoPlayer.STATE_BUFFERING) {
            video_buffering.setVisibility(View.VISIBLE);
        }else if(playbackState == ExoPlayer.STATE_ENDED) {
            refresh_btn.setVisibility(View.VISIBLE);
        }else{
            video_buffering.setVisibility(View.GONE);
        }
    }

    @Override
    public void onPlayerError(ExoPlaybackException error) {
        String errorString = null;
        if (error.type == ExoPlaybackException.TYPE_RENDERER) {
            Exception cause = error.getRendererException();
            if (cause instanceof MediaCodecRenderer.DecoderInitializationException) {
                // Special case for decoder initialization failures.
                MediaCodecRenderer.DecoderInitializationException decoderInitializationException =
                        (MediaCodecRenderer.DecoderInitializationException) cause;
                if (decoderInitializationException.decoderName == null) {
                    if (decoderInitializationException.getCause() instanceof MediaCodecUtil.DecoderQueryException) {
                        errorString = getString(R.string.error_querying_decoders);
                    } else if (decoderInitializationException.secureDecoderRequired) {
                        errorString = getString(R.string.error_no_secure_decoder,
                                decoderInitializationException.mimeType);
                    } else {
                        errorString = getString(R.string.error_no_decoder,
                                decoderInitializationException.mimeType);
                    }
                } else {
                    errorString = getString(R.string.error_instantiating_decoder,
                            decoderInitializationException.decoderName);
                }
            }
        }
        if (errorString != null) {
            Toast.makeText(context, errorString, Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onPositionDiscontinuity() {

    }

    @Override
    public void onPlaybackParametersChanged(PlaybackParameters playbackParameters) {

    }

}
