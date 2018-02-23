package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class JsonUtils {

    public JsonUtils() throws JSONException {

    }

    public static Sandwich parseSandwichJson(String json) {

        try {

            //Create JSONObject

            JSONObject rootObject = new JSONObject(json);

            //Get name JSONObject

            JSONObject subObj = rootObject.getJSONObject("name");

            //Get mainName & alsoKnownAs values

            String mainName = subObj.getString("mainName");

            JSONArray aka = subObj.getJSONArray("alsoKnownAs");

            //Get placeOfOrigin

            String placeOfOrigin = rootObject.getString("placeOfOrigin");

            //Get image

            String imagePath = rootObject.getString("image");

            //Get description

            String description = rootObject.getString("description");

            Log.v("my_tag", "mainName is: " + mainName);
            Log.v("my_tag", "placeOfOrigin is: " + placeOfOrigin);
            Log.v("my_tag", "image is: " + imagePath);
            Log.v("my_tag", "description is: " + description);

            //Get ingredients

            JSONArray ingredientArray = rootObject.getJSONArray("ingredients");

            List<String> alsoKnownAsList = new ArrayList<>();

            //Iterate through the array alsoKnownAs & add it to the list

            for (int i = 0; i < aka.length(); i++) {
                String alsoKnownAs = aka.getString(i);
                alsoKnownAsList.add(alsoKnownAs);
            }

            //Iterate through the array ingredients & add it to the list

            List<String> ingredientList = new ArrayList<>();
            for (int i = 0; i < ingredientArray.length(); i++) {
                ingredientList.add(ingredientArray.getString(i));
            }

            Sandwich sandwich = new Sandwich(mainName, alsoKnownAsList, placeOfOrigin, description,
                    imagePath, ingredientList);

            return sandwich;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}