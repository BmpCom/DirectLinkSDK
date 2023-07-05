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
-repackageclasses "com.sdk.direct.link.open.on.fail.sdk"

-keep class mynew.ClassThatUsesObjectAnimator { *; }

-keepclassmembers class mynew{
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

 -keepattributes Exceptions, InnerClasses, Signature, Deprecated, SourceFile,LineNumberTable, Annotation, EnclosingMethod
 -dontwarn android.webkit.JavascriptInterface

 -keep public class * extends android.view.View{
     *** get*();
     void set*(***);
     public <init>(android.content.Context);
     public <init>(android.content.Context, java.lang.Boolean);
     public <init>(android.content.Context, android.util.AttributeSet);
     public <init>(android.content.Context, android.util.AttributeSet, int);
 }