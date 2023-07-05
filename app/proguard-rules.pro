# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

-assumenosideeffects class android.util.Log {
public static *** d(...);
public static *** v(...);
public static *** i(...);
public static *** w(...);
public static *** e(...);
}
-dontwarn okhttp3.internal.platform.*
-dontwarn android.support.v4.**
-dontwarn com.facebook.ads.**
-keep class android.support.v4.* { *; }

-repackageclasses "sdk.link.open.newn.link.link"

 -keep class sdk.ClassThatUsesObjectAnimator { *; }

-keepclassmembers class sdk{
   public *;
}

-keepclassmembers class * implements android.os.Parcelable {
    public static final android.os.Parcelable$Creator *;
}
-keep public class com.google.android.gms.ads.** {
   public *;
}

-keep class * extends java.util.ListResourceBundle {
    protected Object[][] getContents();
}

-keep public class com.google.android.gms.common.internal.safeparcel.SafeParcelable {
    public static final *** NULL;
}

-keepnames @com.google.android.gms.common.annotation.KeepName class *
-keepclassmembernames class * {
    @com.google.android.gms.common.annotation.KeepName *;
}

-keepnames class * implements android.os.Parcelable {
    public static final ** CREATOR;
}

-dontwarn com.squareup.okhttp.**

-keepclassmembers class sdk{
   public *;
}
-dontwarn org.apache.lang.**

-keep public class com.google.ads.** {
   public *;
}

-keep public class android.support.v7.widget.* { *; }
-keep public class android.support.v7.internal.widget.* { *; }
-keep public class android.support.v7.internal.view.menu.* { *; }

-keep public class * extends android.support.v4.view.ActionProvider {
    public <init>(android.content.Context);
}
-keep class android.support.v7.widget.RoundRectDrawable { *; }


-keep class com.facebook.* { *; }
-keepattributes Signature
 -keep class sdk.ClassThatUsesObjectAnimator { *; }


-keep public class * implements com.bumptech.glide.module.GlideModule
-keep class * extends com.bumptech.glide.module.AppGlideModule {
 <init>(...);
}
-keep public enum com.bumptech.glide.load.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}
-keep class com.bumptech.glide.load.data.ParcelFileDescriptorRewinder$InternalRewinder {
  *** rewind();
}

-keep class com.woxthebox.draglistview.** { *; }
# Picasso
-dontwarn com.squareup.okhttp.**

# Retrofit
-keep class com.google.gson.* { *; }
-keep public class com.google.gson.* {public private protected *;}
-keep class com.google.inject.* { *; }
-keep class org.apache.http.* { *; }
-keep class org.apache.james.mime4j.* { *; }
-keep class javax.inject.* { *; }
-keep class javax.xml.stream.* { *; }
-keep class retrofit.* { *; }
-keep class com.google.appengine.* { *; }
-keepattributes Annotation
-keepattributes Signature
-dontwarn com.squareup.okhttp.*
-dontwarn rx.**
-dontwarn javax.xml.stream.**
-dontwarn com.google.appengine.**
-dontwarn java.nio.file.**
-dontwarn org.codehaus.**

-dontwarn retrofit2.**
-dontwarn org.codehaus.mojo.**
-keep class retrofit2.* { *; }
-keepattributes Exceptions
-keepattributes RuntimeVisibleAnnotations
-keepattributes RuntimeInvisibleAnnotations
-keepattributes RuntimeVisibleParameterAnnotations
-keepattributes RuntimeInvisibleParameterAnnotations

-keep class com.jsteam.logomaker.creator.designapp.model.**{*;}

-keep class androidx.appcompat.widget.* { *; }

-keep public class * implements com.bumptech.glide.module.GlideModule
-keep class * extends com.bumptech.glide.module.AppGlideModule {
 <init>(...);
}
-keep public enum com.bumptech.glide.load.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}
-keep class com.bumptech.glide.load.data.ParcelFileDescriptorRewinder$InternalRewinder {
  *** rewind();
}

# Volley
-dontwarn com.android.volley.**
-dontwarn com.android.volley.error.**
-keep class com.android.volley.* { *; }
-keep class com.android.volley.toolbox.* { *; }
-keep class com.android.volley.Response$* { *; }
-keep class com.android.volley.Request$* { *; }
-keep class com.android.volley.RequestQueue$* { *; }
-keep class com.android.volley.toolbox.HurlStack$* { *; }
-keep class com.android.volley.toolbox.ImageLoader$* { *; }
-keep interface com.android.volley.* { *; }
-keep class org.apache.commons.logging.*

#PDF
-keep class org.spongycastle.** { *; }
-dontwarn org.spongycastle.**
