package com.mohamed265.movieapp.dialogfragment;

import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;

import com.mohamed265.movieapp.R;
import com.mohamed265.movieapp.util.PicassoHandler;

public class ImageViewer extends DialogFragment {

    private String image;
    private ImageView imageView;


    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.imageviewer, container, false);
        imageView = (ImageView) view.findViewById(R.id.imageviwer_imageview);
        PicassoHandler.loadImageWithPicassoFromTMDB(getActivity(), image, imageView);
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        return view;
    }
}
