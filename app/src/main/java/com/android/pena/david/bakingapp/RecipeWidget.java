package com.android.pena.david.bakingapp;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.RemoteViews;

import com.android.pena.david.bakingapp.ui.MainActivity;

import java.util.Random;

/**
 * Implementation of App Widget functionality.
 */
public class RecipeWidget extends AppWidgetProvider {

    private static String WIDGET_RECIPE = "widget_recipe";
    private static String WIDGET_ACTION = "ACTION_GET_RECIPE";

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {
        String[] recipe_names = {"Nutella Pie","Brownies","Yellow Cake","Cheesecake"};
        Random r = new Random();
        int recipe_id = r.nextInt(3)+1;
        Log.e("iD",String.valueOf(recipe_id));


        Intent it = new Intent(context, MainActivity.class);
        it.setAction(WIDGET_ACTION);
        it.putExtra(WIDGET_RECIPE,recipe_id);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,0,it,PendingIntent.FLAG_UPDATE_CURRENT);

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.recipe_widget);
        views.setTextViewText(R.id.recipe_name,recipe_names[recipe_id]);

        views.setOnClickPendingIntent(R.id.widget_image,pendingIntent);

        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

