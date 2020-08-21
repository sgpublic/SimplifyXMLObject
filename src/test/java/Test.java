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
                activities.forEach((object1, index) -> {
                    if (!object1.isTagNull("intent-filter")){
                        String activityName = object1.getStringAttr("name");
                        SXMLObject intentFilter = object1.getXMLObject("intent-filter");
                        if (!intentFilter.isTagNull("action")){
                            SXMLObject action = intentFilter.getXMLObject("action");
                            if (!action.isAttrNull("name")
                                    && action.getStringAttr("name").equals("jwproject.JwIntent.action.ACTION_MAIN")){
                                String actionName = action.getStringAttr("name");
                                System.out.println("main activity is " + packageName + activityName);
                            } else {
                                System.out.println("main activity not found.");
                            }
                        }
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
        return "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
                "<manifest\n" +
                "    package=\"com.sgpublic.bilicheers\">\n" +
                "    <application\n" +
                "        lable=\"@string/app_name\"\n" +
                "        icon=\"@mipmap/ic_launcher\"\n" +
                "        theme=\"@style/material\">\n" +
                "        <activity name=\".MainActivity\">\n" +
                "            <intent-filter>\n" +
                "                <action name=\"jwproject.JwIntent.action.ACTION_MAIN\" />\n" +
                "            </intent-filter>\n" +
                "        </activity>\n" +
                "        <activity name=\".BangumiPlayer\" />\n" +
                "        <activity name=\".LivePlayer\" />\n" +
                "    </application>\n" +
                "</manifest>";
    }
}
