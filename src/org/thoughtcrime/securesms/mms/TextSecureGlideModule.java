package org.thoughtcrime.securesms.mms;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.engine.cache.DiskCache;
import com.bumptech.glide.load.engine.cache.DiskCacheAdapter;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.GlideModule;

import org.thoughtcrime.securesms.glide.OkHttpUrlLoader;
import org.thoughtcrime.securesms.mms.AttachmentStreamUriLoader.AttachmentModel;
import org.thoughtcrime.securesms.mms.ContactPhotoUriLoader.ContactPhotoUri;
import org.thoughtcrime.securesms.mms.DecryptableStreamUriLoader.DecryptableUri;
import org.thoughtcrime.securesms.profiles.AvatarPhotoUriLoader;
import org.thoughtcrime.securesms.profiles.AvatarPhotoUriLoader.AvatarPhotoUri;

import java.io.InputStream;

public class TextSecureGlideModule implements GlideModule {
  @Override
  public void applyOptions(Context context, GlideBuilder builder) {
//    builder.setDiskCache(new NoopDiskCacheFactory());
  }

  @Override
  public void registerComponents(Context context, Glide glide) {
    glide.register(DecryptableUri.class, InputStream.class, new DecryptableStreamUriLoader.Factory());
    glide.register(ContactPhotoUri.class, InputStream.class, new ContactPhotoUriLoader.Factory());
    glide.register(AttachmentModel.class, InputStream.class, new AttachmentStreamUriLoader.Factory());
    glide.register(GlideUrl.class, InputStream.class, new OkHttpUrlLoader.Factory());
    glide.register(AvatarPhotoUri.class, InputStream.class, new AvatarPhotoUriLoader.Factory());
  }

  public static class NoopDiskCacheFactory implements DiskCache.Factory {
    @Override
    public DiskCache build() {
      return new DiskCacheAdapter();
    }
  }
}
