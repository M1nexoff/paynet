## Add project specific ProGuard rules here.
## You can control the set of applied configuration files using the
## proguardFiles setting in build.gradle.
##
## For more details, see
##   http://developer.android.com/guide/developing/tools/proguard.html
#
## If your project uses WebView with JS, uncomment the following
## and specify the fully qualified class name to the JavaScript interface
## class:
##-keepclassmembers class fqcn.of.javascript.interface.for.webview {
##   public *;
##}
#
## Uncomment this to preserve the line number information for
## debugging stack traces.
##-keepattributes SourceFile,LineNumberTable
#
## If you keep the line number information, uncomment this to
## hide the original source file name.
##-renamesourcefileattribute SourceFile
#
#
## Add project specific ProGuard rules here.
## You can control the set of applied configuration files using the
## proguardFiles setting in build.gradle.
##
## For more details, see
##   http://developer.android.com/guide/developing/tools/proguard.html
#
## If your project uses WebView with JS, uncomment the following
## and specify the fully qualified class name to the JavaScript interface
## class:
##-keepclassmembers class fqcn.of.javascript.interface.for.webview {
##   public *;
##}
#
## Uncomment this to preserve the line number information for
## debugging stack traces.
##-keepattributes SourceFile,LineNumberTable
#
## If you keep the line number information, uncomment this to
## hide the original source file name.
##-renamesourcefileattribute SourceFile
#
#-keep @interface uz.gita.m1nex.core.ScreenModelImpl { *; }
#-keepattributes *Annotation*
#
##-keep,allowobfuscation class ** extends uz.gita.m1nex.presenter.AppViewModel
#
#
## Retrofit does reflection on generic parameters. InnerClasses is required to use Signature and
## EnclosingMethod is required to use InnerClasses.
#-keepattributes Signature, InnerClasses, EnclosingMethod
## Keep Hilt components
#-keep class dagger.hilt.** { *; }
#-keep class * extends dagger.hilt.android.components.** { *; }
#-keep class * extends dagger.hilt.components.** { *; }
#
## Retrofit does reflection on method and parameter annotations.
#-keepattributes RuntimeVisibleAnnotations, RuntimeVisibleParameterAnnotations
#
## Keep annotation default values (e.g., retrofit2.http.Field.encoded).
#-keepattributes AnnotationDefault
#
## Retain service method parameters when optimizing.
#-keepclassmembers,allowshrinking,allowobfuscation interface * {
#    @retrofit2.http.* <methods>;
#}
#
## Ignore annotation used for build tooling.
#-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement
## Keep all classes in the use case package
#-keep class uz.gita.m1nex.usecase.card.* { *; }
#-keep class uz.gita.m1nex.usecase.password.* { *; }
#-keep class uz.gita.m1nex.usecase.home.* { *; }
#-keep class uz.gita.m1nex.usecase.signup.* { *; }
#-keep class uz.gita.m1nex.usecase.splash.* { *; }
#-keep class uz.gita.m1nex.usecase.di.*
## Ignore JSR 305 annotations for embedding nullability information.
#-dontwarn javax.annotation.**
#
## Guarded by a NoClassDefFoundError try/catch and only used when on the classpath.
#-dontwarn kotlin.Unit
#
## Top-level functions that can only be used by Kotlin.
#-dontwarn retrofit2.KotlinExtensions
#-dontwarn retrofit2.KotlinExtensions$*
#
## With R8 full mode, it sees no subtypes of Retrofit interfaces since they are created with a Proxy
## and replaces all potential values with null. Explicitly keeping the interfaces prevents this.
#-if interface * { @retrofit2.http.* <methods>; }
#-keep,allowobfuscation interface <1>
#
## Keep inherited services.
#-if interface * { @retrofit2.http.* <methods>; }
#-keep,allowobfuscation interface * extends <1>
#
## With R8 full mode generic signatures are stripped for classes that are not
## kept. Suspend functions are wrapped in continuations where the type argument
## is used.
#-keep,allowobfuscation,allowshrinking class kotlin.coroutines.Continuation
#
## R8 full mode strips generic signatures from return types if not kept.
#-if interface * { @retrofit2.http.* public *** *(...); }
#-keep,allowoptimization,allowshrinking,allowobfuscation class <3>
#
## With R8 full mode generic signatures are stripped for classes that are not kept.
##-keep,allowobfuscation,allowshrinking class retrofit2.Response

# Keep all UseCase interfaces and implementations (adjust to your package structure)
-keep interface uz.gita.m1nex.usecase.** { *; }
-keep interface uz.gita.m1nex.usecase.di.UseCaseModule { *; }
-keep interface uz.gita.m1nex.usecase.di.HiltWrapper_UseCaseModule { *; }
-keep class uz.gita.m1nex.usecase.** { *; }
-keep interface uz.gita.m1nex.usecase.** { *; }
-dontwarn uz.gita.m1nex.usecase.**
# Keep classes that are bound in the UseCaseModule
-keep class uz.gita.m1nex.usecase.splash.SplashUseCaseImpl { *; }
-keep class uz.gita.m1nex.usecase.signup.AuthUseCaseImpl { *; }
-keep class uz.gita.m1nex.usecase.home.HomeUseCaseImpl { *; }
-keep class uz.gita.m1nex.usecase.card.CardUseCaseImpl { *; }

# Ensure that Hilt components and generated classes are kept
-keep class dagger.hilt.** { *; }
-keep class dagger.hilt.android.** { *; }

# Keep annotations (such as @Inject) that are used by Hilt
-keepattributes Annotation
-keepattributes Signature

# Keep Binds for Dagger multibinding
-keepclassmembers,allowobfuscation interface * {
    @dagger.Binds *;
}

# Optional: Keep Log statements if you want to retain logging
-assumenosideeffects class android.util.Log {
    public static *** d(...);
    public static *** v(...);
    public static *** i(...);
    public static *** w(...);
    public static *** e(...);
}
