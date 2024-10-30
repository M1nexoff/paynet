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
-keepattributes RuntimeVisibleAnnotations
-keepattributes Signature
-keepattributes Annotation
-dontwarn uz.gita.m1nex.presenter.screenmodel.**
-dontwarn uz.gita.m1nex.presenter.di.**
-keep @interface uz.gita.m1nex.core.ScreenModelImpl
-keep class **uz.gita.m1nex.core.ScreenModelImpl { *; }
-keep class uz.gita.m1nex.presenter.** { *; }
-keep interface uz.gita.m1nex.presenter.** { *; }
-keep class ** {
    @uz.gita.m1nex.core.ScreenModelImpl *;
}
# Keep the custom @ScreenModelKey annotation
-keep @interface cafe.adriel.voyager.hilt.ScreenModelKey

# Keep all classes annotated with @ScreenModelKey to prevent them from being obfuscated
-keepclasseswithmembers class * {
    @cafe.adriel.voyager.hilt.ScreenModelKey *;
}

# Keep all classes that are used in Dagger multibinding with @IntoMap
-keepclasseswithmembers class * {
    @dagger.multibindings.IntoMap *;
}

# Ensure that Dagger-generated code and Hilt components are kept
-keep class dagger.hilt.** { *; }
-keep class dagger.hilt.android.** { *; }
-keep class dagger.multibindings.** { *; }

# Keep all classes used in ScreenModelModule
-keep class uz.gita.m1nex.presenter.screenmodel.** { *; }

-keep class ** extends uz.gita.m1nex.presenter.AppViewModel
-keep class uz.gita.m1nex.presenter.di.ScreenModelModule { *; }
-keep class uz.gita.m1nex.presenter.di.ScreenModelModule

-keep class uz.gita.m1nex.presenter.screenmodel.**


# Retrofit does reflection on generic parameters. InnerClasses is required to use Signature and
# EnclosingMethod is required to use InnerClasses.
-keepattributes Signature, InnerClasses, EnclosingMethod
# Keep Hilt components
-keep class dagger.hilt.** { *; }
-keep class * extends dagger.hilt.android.components.** { *; }
-keep class * extends dagger.hilt.components.** { *; }

# Retrofit does reflection on method and parameter annotations.
-keepattributes RuntimeVisibleAnnotations, RuntimeVisibleParameterAnnotations

# Keep annotation default values (e.g., retrofit2.http.Field.encoded).
-keepattributes AnnotationDefault

# Retain service method parameters when optimizing.
-keepclassmembers,allowshrinking,allowobfuscation interface * {
    @retrofit2.http.* <methods>;
}

# Ignore annotation used for build tooling.
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement

# Ignore JSR 305 annotations for embedding nullability information.
-dontwarn javax.annotation.**

# Guarded by a NoClassDefFoundError try/catch and only used when on the classpath.
-dontwarn kotlin.Unit

# Top-level functions that can only be used by Kotlin.
-dontwarn retrofit2.KotlinExtensions
-dontwarn retrofit2.KotlinExtensions$*

# With R8 full mode, it sees no subtypes of Retrofit interfaces since they are created with a Proxy
# and replaces all potential values with null. Explicitly keeping the interfaces prevents this.
-if interface * { @retrofit2.http.* <methods>; }
-keep,allowobfuscation interface <1>

# Keep inherited services.
-if interface * { @retrofit2.http.* <methods>; }
-keep,allowobfuscation interface * extends <1>

# With R8 full mode generic signatures are stripped for classes that are not
# kept. Suspend functions are wrapped in continuations where the type argument
# is used.
-keep,allowobfuscation,allowshrinking class kotlin.coroutines.Continuation

# R8 full mode strips generic signatures from return types if not kept.
-if interface * { @retrofit2.http.* public *** *(...); }
-keep,allowoptimization,allowshrinking,allowobfuscation class <3>