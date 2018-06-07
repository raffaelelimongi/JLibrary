package vo;

import javafx.scene.image.ImageView;

public class ImmagineDati
{
    private ImageView imageView;
    String nomeimg;

    public ImmagineDati(String nomeimg,ImageView imageView)
    {
        this.nomeimg=nomeimg;
        this.imageView=imageView;
    }

    public String getNomeimg() {
        return nomeimg;
    }

    public void setNomeimg(String nomeimg) {
        this.nomeimg = nomeimg;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }
}
