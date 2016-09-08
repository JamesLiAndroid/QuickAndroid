# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in /home/jyj-lsy/AndroidDev/android-sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

#　基础混淆
#指定代码的压缩级别
-optimizationpasses 5
#包明不混合大小写
-dontusemixedcaseclassnames
#不去忽略非公共的库类
-dontskipnonpubliclibraryclasses
#优化  不优化输入的类文件
-dontoptimize
#预校验
-dontpreverify
# 混淆时是否记录日志
-verbose
#混淆时所采用的算法
-optimizations !code/simplification/arithmetic,!field/,!class/merging/
#保护注解
-keepattributes Annotation
# 保持哪些类不被混淆
-keep public class  extends android.app.Fragment-keep public class  extends android.app.Activity-keep public class  extends android.app.Application-keep public class  extends android.app.Service-keep public class  extends android.content.BroadcastReceiver-keep public class  extends android.content.ContentProvider-keep public class  extends android.app.backup.BackupAgentHelper-keep public class  extends android.preference.Preference-keep public class com.android.vending.licensing.ILicensingService
#如果有引用v4包可以添加下面这行
-keep public class * extends android.support.v4.app.Fragment
#忽略警告
-ignorewarning
###############记录生成的日志数据,gradle build时在本项目根目录输出
#apk 包内所有 class 的内部结构
#-dump class_files.txt
#未混淆的类和成员
-printseeds seeds.txt
#列出从 apk 中删除的代码
#-printusage unused.txt
#混淆前后的映射
#-printmapping mapping.txt
#如果引用了v4或者v7包
-dontwarn android.support.**
#禁止优化泛型
-keepattributes Signature
#保持 native 方法不被混淆
-keepclasseswithmembernames class * {    native <methods>;}
#保持自定义控件类不被混淆
-keepclasseswithmembers class * {    public <init>(android.content.Context, android.util.AttributeSet);    public <init>(android.content.Context, android.util.AttributeSet, int);}
#保持自定义控件类不被混淆
-keepclassmembers class  extends android.app.Activity {   public void (android.view.View);}
#保持 Parcelable 不被混淆
-keep class  implements android.os.Parcelable {  public static final android.os.Parcelable$Creator ;}-keep public class  extends android.view.View {        public <init>(android.content.Context);        public <init>(android.content.Context, android.util.AttributeSet);        public <init>(android.content.Context, android.util.AttributeSet, int);        public void set(...);}
#保持 Serializable 不被混淆
#-keepnames class * implements java.io.Serializable
#保持 Serializable 不被混淆并且enum 类也不被混淆
#-keepclassmembers class * implements java.io.Serializable {
#    static final long serialVersionUID;
#    private static final java.io.ObjectStreamField[] serialPersistentFields;
#    !static !transient <fields>;
#    !private <fields><span></span>;
#    !private <methods>;
#    private void writeObject(java.io.ObjectOutputStream);
#    private void readObject(java.io.ObjectInputStream);
#    java.lang.Object writeReplace();
#    java.lang.Object readResolve();
#}
#保持枚举 enum 类不被混淆 如果混淆报错，建议直接使用上面的 -keepclassmembers class * implements java.io.Serializable即可
-keepclassmembers enum  {  public static *[] values();  public static * valueOf(java.lang.String);}-keepclassmembers class  {    public void *ButtonClicked(android.view.View);}
#不混淆资源类
-keepclassmembers class *.R$ {    public static <fields>;}
# 基础混淆结束

# Retrofit混淆
# Platform calls Class.forName on types which do not exist on Android to determine platform.
-dontnote retrofit2.Platform
# Platform used when running on RoboVM on iOS. Will not be used at runtime.
-dontnote retrofit2.Platform$IOS$MainThreadExecutor
# Platform used when running on Java 8 VMs. Will not be used at runtime.
-dontwarn retrofit2.Platform$Java8
# Retain generic type information for use by reflection by converters and adapters.
-keepattributes Signature
# Retain declared checked exceptions for use by a Proxy instance.
-keepattributes Exceptions
# Retrofit混淆结束

# OkHttp混淆
-dontwarn com.squareup.okhttp.**
-keep class com.squareup.okhttp.** { *;}
-dontwarn okio.**
# OkHttp混淆结束

# Gson开启混淆
-keep class com.google.**{*;}
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

##---------------Begin: proguard configuration for Gson  ----------
# Gson uses generic type information stored in a class file when working with fields. Proguard
# removes such information by default, so configure it to keep all of it.
-keepattributes Signature
# Gson specific classes
-keep class sun.misc.Unsafe { *; }
#-keep class com.google.gson.stream.** { *; }
# Application classes that will be serialized/deserialized over Gson
-keep class com.google.gson.examples.android.model.** { *; }  ##这里需要改成解析到哪个  javabean

##---------------End: proguard configuration for Gson  ----------
# Gson混淆结束

# LeakCanany混淆，在debug版本使用了混淆生效
#-keep class org.eclipse.mat.** { *; }
#-keep class com.squareup.leakcanary.** { *; }
# LeakCanary混淆完成

# Glide混淆
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}
-keepresourcexmlelements manifest/application/meta-data@value=GlideModule
# Glide混淆结束

# ButterKnife混淆
-keep class butterknife.** { *; }
-dontwarn butterknife.internal.**
-keep class **$$ViewBinder { *; }

-keepclasseswithmembernames class * {
    @butterknife.* <fields>;
}

-keepclasseswithmembernames class * {
    @butterknife.* <methods>;
}
#ButterKnife混淆结束

# GreenDao混淆
-keep class de.greenrobot.dao.** {*;}
-keepclassmembers class * extends de.greenrobot.dao.AbstractDao {
    public static java.lang.String TABLENAME;
}
-keep class **$Properties
#GreenDao混淆完成

-keepnames class * implements java.io.Serializable

-keepclassmembers class * implements java.io.Serializable {

static final long serialVersionUID;

private static final java.io.ObjectStreamField[] serialPersistentFields;

!static !transient ;

private void writeObject(java.io.ObjectOutputStream);

private void readObject(java.io.ObjectInputStream);

java.lang.Object writeReplace();

java.lang.Object readResolve();

}
