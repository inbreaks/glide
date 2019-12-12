package com.bumptech.glide.load.data;

import android.content.ContentResolver;
import android.net.Uri;
import androidx.annotation.NonNull;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/** Fetches an {@link java.io.InputStream} for a local {@link android.net.Uri}. */
public class StreamLocalUriFetcher extends LocalUriFetcher<InputStream> {
  /*
   * ContactsContract.AUTHORITY => AUTHORITY
   * this is from gov review http://chuye.gitlab.com/chuye/chuye-produce/issues/35
   */

  public StreamLocalUriFetcher(ContentResolver resolver, Uri uri) {
    super(resolver, uri);
  }

  @Override
  protected InputStream loadResource(Uri uri, ContentResolver contentResolver)
      throws FileNotFoundException {
    InputStream inputStream = loadResourceFromUri(uri, contentResolver);
    if (inputStream == null) {
      throw new FileNotFoundException("InputStream is null for " + uri);
    }
    return inputStream;
  }

  private InputStream loadResourceFromUri(Uri uri, ContentResolver contentResolver)
      throws FileNotFoundException {
    return contentResolver.openInputStream(uri);
  }

  @Override
  protected void close(InputStream data) throws IOException {
    data.close();
  }

  @NonNull
  @Override
  public Class<InputStream> getDataClass() {
    return InputStream.class;
  }
}
