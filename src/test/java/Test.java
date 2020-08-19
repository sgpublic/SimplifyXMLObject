import com.sgpublic.xml.SXMLArray;
import com.sgpublic.xml.SXMLObject;
import com.sgpublic.xml.exception.SXMLException;

public class Test {
    public static void main(String[] args) {
        try {
            String xml = getString();
            SXMLObject object = new SXMLObject(xml);

            if (!object.isAttrNull("package")){
                String packageName = object.getStringAttr("package");
                System.out.println("package is " + packageName);

                SXMLObject application = object.getXMLObject("application");
                SXMLArray activities = application.getXMLArray("activity");
                activities.forEach(new SXMLArray.ForEachEvent() {
                    @Override
                    public void onEachItem(SXMLObject object, int index) throws SXMLException {
                        if (!object.isTagNull("intent-filter")){
                            String activityName = object.getStringAttr("android:name");
                            SXMLObject intentFilter = object.getXMLObject("intent-filter");
                            if (!intentFilter.isTagNull("action")){
                                SXMLObject action = intentFilter.getXMLObject("action");
                                if (!action.isAttrNull("android:name")){
                                    String actionName = action.getStringAttr("android:name");
                                    System.out.println("main activity is " + packageName + activityName);
                                } else {
                                    System.out.println("main activity not found.");
                                }
                            }
                        }
                    }

                    @Override
                    public void onError(SXMLException e) {
                        e.printStackTrace();
                    }
                });
            } else {
                System.out.println("package value not found.");
            }
        } catch (SXMLException e) {
            e.printStackTrace();
        }
    }

    private static String getString(){
        return "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<manifest xmlns:android=\"http://schemas.android.com/apk/res/android\"\n" +
                "    xmlns:tools=\"http://schemas.android.com/tools\"\n" +
                "    package=\"com.sgpublic.bilidownload\">\n" +
                "\n" +
                "    <uses-permission android:name=\"android.permission.READ_PHONE_STATE\" />\n" +
                "    <uses-permission android:name=\"android.permission.INTERNET\" />\n" +
                "    <uses-permission android:name=\"android.permission.WRITE_EXTERNAL_STORAGE\" />\n" +
                "\n" +
                "    <application\n" +
                "        android:name=\".BaseService.Main\"\n" +
                "        android:icon=\"@mipmap/ic_launcher\"\n" +
                "        tools:ignore=\"AllowBackup,GoogleAppIndexingWarning,InnerclassSeparator\"\n" +
                "        tools:targetApi=\"q\">\n" +
                "        <activity android:name=\".License\" />\n" +
                "        <activity android:name=\".Search\" />\n" +
                "        <activity android:name=\".OtherFollows\" />\n" +
                "        <activity android:name=\".Season\" />\n" +
                "        <activity\n" +
                "            android:name=\".Main\"\n" +
                "            android:launchMode=\"singleTop\" />\n" +
                "        <activity\n" +
                "            android:name=\".Welcome\"\n" +
                "            android:launchMode=\"singleTop\">\n" +
                "            <intent-filter>\n" +
                "                <action android:name=\"android.intent.action.MAIN\" />\n" +
                "\n" +
                "                <category android:name=\"android.intent.category.LAUNCHER\" />\n" +
                "            </intent-filter>\n" +
                "        </activity>\n" +
                "    </application>\n" +
                "</manifest>";
    }
}
