package to.charlie.conferenceapp.model.util;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;

public class ResourceUtil
{
	private static final String ASSET_MISSING_TAG = "ASSET_MISSING";

	public static void setImageOnImageView(ImageView imageView, String fileName,
																				 AssetManager assetManager)
	{
		try (InputStream is = assetManager.open("images/" + fileName + ".jpg"))
		{
			Bitmap bitmap = BitmapFactory.decodeStream(is);
			imageView.setImageBitmap(bitmap);
		} catch (IOException e)
		{
			Log.e(ASSET_MISSING_TAG, "Error opening image file", e);
		}
	}

}
