package com.udacity.sandwichclub.utils;

import android.support.annotation.NonNull;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class JsonUtils {

    /*
{
    "name":
    {
        "mainName":"Club sandwich",
        "alsoKnownAs":["Clubhouse sandwich"]
    },
    "placeOfOrigin":"United States",
    "description":"A club sandwich, also called a clubhouse sandwich, is a sandwich of bread (occasionally toasted), sliced cooked poultry, fried bacon, lettuce, tomato, and mayonnaise. It is often cut into quarters or halves and held together by cocktail sticks. Modern versions frequently have two layers which are separated by an additional slice of bread.",
    "image":"https://upload.wikimedia.org/wikipedia/commons/thumb/4/4f/Club_sandwich.png/800px-Club_sandwich.png",
    "ingredients":["Toasted bread","Turkey or chicken","Bacon","Lettuce","Tomato","Mayonnaise"]
}
     */
    public static Sandwich parseSandwichJson(String json) {

        String mainName = null;
        List<String> alsoKnownAs = null;
        String placeOfOrigin = null;
        String description = null;
        String image = null;
        List<String> ingredients = null;

        try {
            JSONObject jSandwich = new JSONObject(json);

            JSONObject jName = jSandwich.getJSONObject("name");
            mainName = jName.getString("mainName");
            JSONArray jAlsoKnownAsArray = jName.getJSONArray("alsoKnownAs");
            if(jAlsoKnownAsArray != null) {
                alsoKnownAs = new ArrayList<String>();
                for (int i = 0; i < jAlsoKnownAsArray.length(); i++) {
                    alsoKnownAs.add(jAlsoKnownAsArray.getString(i));
                }
            }

            placeOfOrigin = jSandwich.getString("placeOfOrigin");
            description = jSandwich.getString("description");
            image = jSandwich.getString("image");

            JSONArray jIngredients = jSandwich.getJSONArray("ingredients");
            if(jIngredients != null) {
                ingredients = new ArrayList<String>();
                for (int i = 0; i < jIngredients.length(); i++) {
                    ingredients.add(jIngredients.getString(i));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

        return new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, image, ingredients);
    }
}
