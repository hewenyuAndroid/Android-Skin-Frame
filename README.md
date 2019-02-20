### 插件式换肤
很多应用都支持换肤的功能，关于换肤功能的实现，一般实现换肤功能有两个途径:
> 1. 在应用内部设置两套或多套主题，点击换肤的时候切换对应的主题；
> 2. 通过 `Resources` 对象加载本地的皮肤资源（实际上为apk文件）;

使用第一种方式换肤相对来说没有那么的灵活，而且通常会增加 apk 的体积，而使用第二种插件式换肤则可以实现类似QQ、网易云音乐等应用的换肤方式；

### 效果图
![效果一](https://github.com/hewenyuAndroid/Android-Skin-Frame/blob/master/screen/show_single.gif?raw=true)
![效果二](https://github.com/hewenyuAndroid/Android-Skin-Frame/blob/master/screen/show_all.gif?raw=true)

### 功能说明
本项目通过插件化实现换肤的功能:
1. 默认支持 `textColor`、`background`、`src` 三个属性换肤，支持其它属性扩展为默认属性；
2. 支持将使用频率较高的自定义属性扩展到默认支持的属性中；
3. 支持使用频率比较低的自定义属性单独适配；

[测试apk](https://github.com/hewenyuAndroid/Android-Skin-Frame/raw/master/apk/app-debug.apk)
[资源包](https://github.com/hewenyuAndroid/Android-Skin-Frame/raw/master/apk/target.skin)

> compile 'com.hewenyu:SkinSupport:1.0'

### 实现思路
1. 获取皮肤文件: 插件式换肤的插件实际上是一个包含res目录下面资源的apk包，我们只需要将该 apk 包下载到手机的本地然后通过创建目标文件的 `Resources` 对象即可拿到对应的资源;
2. 更新皮肤属性: 拿到资源文件后我们需要将所有控件对应的换肤属性资源进行替换，这里我们需要拦截页面中所有的 `View` 的创建来获取对应的换肤属性，这里关于 `View` 对象创建的拦截可以查看我的 [Android 布局文件加载 LayoutInflater 源码解析](https://www.jianshu.com/p/846c36af2c2b);
3. 缓存更新的皮肤 (这里缓存皮肤的路径即可) ，方便下次打开的时候直接换上该皮肤；

### 使用方式
1. 我们可以将`BaseActivity` 抽取出来一层 `SkinActivity`用来配置皮肤相关：
```Java
// 这里需要实现 OnSkinChangeCallback 接口用来扩展自定义View的换肤
public abstract class SkinActivity extends AppCompatActivity implements OnSkinChangeCallback {
    // 拦截View创建代码的封装对象
    protected SkinCompat mSkinCompat;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        // 配置 Factory 拦截View的创建
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        LayoutInflaterCompat.setFactory2(layoutInflater, this);
    
        mSkinCompat = new SkinCompat(this, getWindow());
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        // 拦截View的创建
        View view = mSkinCompat.createView(parent, name, context, attrs);
        // 解析View声明的属性
        if (view != null) {
            // 获取当前View可以换肤的属性列表
            List<SkinAttr> skinAttrs = SkinAttrSupport.getSkinAttr(context, attrs);
            // 将获取到的属性和对应的 View 封装
            SkinAttrHolder skinAttrHolder = new SkinAttrHolder(view, skinAttrs);
            // 统一交给 SkinManager 管理
            mSkinCompat.managerSkinView(this, skinAttrHolder);
            // 判断是否需要换肤
            SkinManager.getInstance().checkSkin(this, view, skinAttrHolder);
        }
        return view;
    }

    // 这里单独的配置自定义View的换肤功能
    @Override
    public void onSkinChange(View view, SkinResource skinResource) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 释放内存
        mSkinCompat.release(this);
    }
}
```

2. 调用换肤/恢复默认皮肤的方法，如果是SD卡中的皮肤读取需要配置读取本地文件的权限:
```Java
// 换肤
SkinManager.getInstance().loadSkin(本地皮肤路径);
// 恢复默认皮肤
SkinManager.getInstance().restoreSkin();
```

3. 扩展自定义View的属性换肤只需要重写`onSkinChange()` 方法即可:
```Java
 @Override
public void onSkinChange(View view, SkinResource skinResource) {
    super.onSkinChange(view, skinResource);
    ColorStateList color = null;
    switch (view.getId()) {
        case R.id.my_view:
            // 这里传入的名称为资源的名称而不是属性的名称
            color = skinResource.getColorByName("main_color");
            ((MyView) view).setColor(color.getDefaultColor());
            break;
    }
}
```

4. 将使用频率高的自定义属性配置成默认属性可以参考 [SkinType](https://github.com/hewenyuAndroid/Android-Skin-Frame/blob/master/SkinSupport/src/main/java/com/hwy/skin/attr/SkinType.java) 类，然后将写好的类配置到 `SkinManager` 中即可:
```Java
SkinManager.getInstance().exchangeSkinType(Class<? extend ISkinType> skinTypeClass);
```