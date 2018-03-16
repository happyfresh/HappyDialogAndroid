# HappyDialogAndroid
[![Release](https://jitpack.io/v/happyfresh/HappyDialogAndroid.svg)](https://jitpack.io/#happyfresh/HappyDialogAndroid) ![Downloads](https://jitpack.io/v/happyfresh/HappyDialogAndroid/month.svg)

Template for showing dialog

## How to Get
To get HappyDialog into your build:

<b>Step 1.</b> Add the JitPack repository to your build file
```gradle
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}
```
<b>Step 2.</b> Add the dependency
```gradle
dependencies {
        compile 'com.github.happyfresh:HappyDialogAndroid:1.0.0'
}
```

### How to Use

Define HappyDialogButton Type first

```java
enum ButtonType implements HappyDialogButton.Type {
  PRIMARY(0, R.color.colorPrimary, android.R.color.white);

  int id;

  int background;

  int textColor;

  ButtonType(int id, int background, int textColor) {
      this.id = id;
      this.background = background;
      this.textColor = textColor;
  }

  @Override
  public int getId() {
      return this.id;
  }

  @NotNull
  @Override
  public Drawable getBackground() {
      return ContextCompat.getDrawable(App.sInstance, this.background);
  }

  @Override
  public int getTextColor() {
      return ContextCompat.getColor(App.sInstance, this.textColor);
  }
}
```

And then ready for using HappyDialog with HappyDialogBuilder

```java
HappyDialogBuilder builder = new HappyDialogBuilder(v.getContext());
builder.setFeatureImage(R.drawable.ic_launcher_background);
builder.setTitle("TITLE");
builder.setMessage("MESSAGE");
builder.setCustomView(R.layout.custom_view);
builder.addButton(ButtonType.PRIMARY, "Test", new HappyDialogButton.OnClickListener() {
    @Override
    public void onClick(@NotNull View view, @NotNull HappyDialogButton button,
            @NotNull HappyDialogInterface dialog) {
        Toast.makeText(MainActivity.this, "Hallo", Toast.LENGTH_LONG).show();
        dialog.dismiss();
    }
});
builder.addOnCancelListener(new HappyDialogInterface.OnCancelListener() {
    @Override
    public void onCancel(@NotNull HappyDialogInterface dialog) {
        Toast.makeText(MainActivity.this, "onCancel", Toast.LENGTH_LONG).show();
    }
});
builder.addOnDismissListener(new HappyDialogInterface.OnDismissListener() {
    @Override
    public void onDismiss(@NotNull HappyDialogInterface dialog) {
        Toast.makeText(MainActivity.this, "onDismiss", Toast.LENGTH_LONG).show();
    }
});
builder.show();
```

### Methods
* setFeatureImage (Drawable / drawableResId) / getFeatureImage() as Drawable
* setTitle (String / stringResId) / getTitle() as String
* setMessage (String / stringResId) / getMessage() as String
* addButton (HappyDialogButton)
* addButton (HappyDialogButton.Type, String, onClickListener)
* addButton (HappyDialogButton.Type, stringResId, onClickListener)
* setCancelable(Boolean) / getCancelable() as Boolean
* addCancelListener(onCancelListener)
* addDismissListener(onDismissListener)
* create() as Dialog
* show() as Dialog
