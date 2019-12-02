package to.charlie.conferenceapp.model.util;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;

public class ResourceUtil
{
	/**
	 * Log tag to identify a call to the logs where an image failed to load
	 */
	private static final String ASSET_MISSING_TAG = "ASSET_MISSING";

	static void setImageOnImageView(ImageView imageView, String fileName,
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

	/**
	 * loads the image at filename and sets that onto the provided ImageView. The fetching of the
	 * image is done asynchronously.
	 *
	 * @param imageView    The image to update
	 * @param fileName     The pathname to the image
	 * @param assetManager The asset manager that should be used to load the image. Comes from the
	 *                     context
	 */
	public static void setImageOnImageViewAsync(ImageView imageView, String fileName, AssetManager assetManager)
	{
		new SetImageViewAsyncTask(imageView, assetManager, fileName).execute();
	}

	private static class SetImageViewAsyncTask extends AsyncTask<Void, Void, Bitmap>
	{
		private ImageView imageView;
		private AssetManager assetManager;
		private String filename;

		SetImageViewAsyncTask(ImageView imageView, AssetManager assetManager, String filename)
		{
			this.imageView = imageView;
			this.assetManager = assetManager;
			this.filename = filename;
		}

		/**
		 * Runs asynchronously
		 *
		 * @param args Don't have any real args
		 * @return The loaded image converted into a bitmap
		 */
		@Override
		protected Bitmap doInBackground(Void... args)
		{
			try (InputStream is = assetManager.open(filename))
			{
				return BitmapFactory.decodeStream(is);
			} catch (IOException e)
			{
				Log.e(ASSET_MISSING_TAG, "Error opening image file", e);
			}

			return null;
		}

		/**
		 * We can now add the bitmap to our imageView in the UI thread
		 *
		 * @param bitmap The bitmap to add onto the image view
		 */
		@Override
		protected void onPostExecute(Bitmap bitmap)
		{
			imageView.setImageBitmap(bitmap);
		}
	}
}
