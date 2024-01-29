package com.tzappstudio.autotouchpro.services.task;

import android.graphics.Rect;
import android.util.Log;

import com.tzappstudio.autotouchpro.command.Command;
import com.tzappstudio.autotouchpro.command.CommandParser;
import com.tzappstudio.autotouchpro.command.ICommand;
import com.tzappstudio.autotouchpro.services.AccessibilityServices;
import com.tzappstudio.autotouchpro.services.IScreenshotProvider;
import com.tzappstudio.autotouchpro.services.task.apktask.GetVersionAppByPackage;
import com.tzappstudio.autotouchpro.services.task.apktask.InstallAPKViaIntent;
import com.tzappstudio.autotouchpro.services.task.apktask.InstallAPKViaUpdater;
import com.tzappstudio.autotouchpro.services.task.apktask.UninstallAPKViaUpdater;
import com.tzappstudio.autotouchpro.services.task.apptask.CheckUsbDebuggingModeTask;
import com.tzappstudio.autotouchpro.services.task.apptask.GetCPUTemperatureTask;
import com.tzappstudio.autotouchpro.services.task.apptask.GetRunButtonStatusTask;
import com.tzappstudio.autotouchpro.services.task.apptask.GetRunningAppTask;
import com.tzappstudio.autotouchpro.services.task.apptask.GetSerialTask;
import com.tzappstudio.autotouchpro.services.task.apptask.OpenAirPlaneSettingsTask;
import com.tzappstudio.autotouchpro.services.task.apptask.OpenFreeBrowserTask;
import com.tzappstudio.autotouchpro.services.task.apptask.OpenIntentSettingsTask;
import com.tzappstudio.autotouchpro.services.task.apptask.StopAppTask;
import com.tzappstudio.autotouchpro.services.task.apptask.TurnOffFlashTask;
import com.tzappstudio.autotouchpro.services.task.apptask.TurnOnFlashTask;
import com.tzappstudio.autotouchpro.services.task.contact.AddContactTask;
import com.tzappstudio.autotouchpro.services.task.contact.ImportContactFromCsvTask;
import com.tzappstudio.autotouchpro.services.task.csvtask.DataRowTask;
import com.tzappstudio.autotouchpro.services.task.exposetask.ChangeInfoTask;
import com.tzappstudio.autotouchpro.services.task.exposetask.GetXposedStatusTask;
import com.tzappstudio.autotouchpro.services.task.filetask.DeleteFileTask;
import com.tzappstudio.autotouchpro.services.task.filetask.EncryptFileTask;
import com.tzappstudio.autotouchpro.services.task.filetask.GetAllInfoTask;
import com.tzappstudio.autotouchpro.services.task.filetask.JsonToFileTask;
import com.tzappstudio.autotouchpro.services.task.filetask.LogToFileTask;
import com.tzappstudio.autotouchpro.services.task.filetask.UpdateConfigFileTask;
import com.tzappstudio.autotouchpro.services.task.image.GetGifImageTask;
import com.tzappstudio.autotouchpro.services.task.image.GetImageTask;
import com.tzappstudio.autotouchpro.services.task.image.ResizeImageTask;
import com.tzappstudio.autotouchpro.services.task.image.ScreenshotNonRootTask;
import com.tzappstudio.autotouchpro.services.task.image.ScreenshotTask;
import com.tzappstudio.autotouchpro.services.task.network.Force3GNetworkTask;
import com.tzappstudio.autotouchpro.services.task.network.Force4GLTETask;
import com.tzappstudio.autotouchpro.services.task.network.GetIPPublicTask;
import com.tzappstudio.autotouchpro.services.task.network.GetLocalIPWifiTask;
import com.tzappstudio.autotouchpro.services.task.network.GetSignalStrengthDataNetworkTask;
import com.tzappstudio.autotouchpro.services.task.network.GetStatusDataConnectionTask;
import com.tzappstudio.autotouchpro.services.task.random.FirstNameRandomTask;
import com.tzappstudio.autotouchpro.services.task.random.GetTimeStampTask;
import com.tzappstudio.autotouchpro.services.task.random.LastNameRandomTask;
import com.tzappstudio.autotouchpro.services.task.random.NumberRandomTask;
import com.tzappstudio.autotouchpro.services.task.random.PhoneRandomTask;
import com.tzappstudio.autotouchpro.services.task.random.RandomEmailTask;
import com.tzappstudio.autotouchpro.services.task.random.RandomPassWordTask;
import com.tzappstudio.autotouchpro.services.task.random.RandomTask;
import com.tzappstudio.autotouchpro.services.task.random.TextRandomTask;
import com.tzappstudio.autotouchpro.services.task.random.UserAgentRandomTask;
import com.tzappstudio.autotouchpro.services.task.reflection.common.CopyToClipboardTask;
import com.tzappstudio.autotouchpro.services.task.reflection.dbtask.UpdateDBConfigTask;
import com.tzappstudio.autotouchpro.services.task.reflection.remote_shell.RemoteADBShellTask;
import com.tzappstudio.autotouchpro.services.task.retrofittask.DownloadFileTask;
import com.tzappstudio.autotouchpro.services.task.retrofittask.GetJsonFromUrlTask;
import com.tzappstudio.autotouchpro.services.task.retrofittask.SubmitDataTask;
import com.tzappstudio.autotouchpro.services.task.retrofittask.UploadFile;
import com.tzappstudio.autotouchpro.services.task.roottask.BackupTask;
import com.tzappstudio.autotouchpro.services.task.roottask.ClearDataTask;
import com.tzappstudio.autotouchpro.services.task.roottask.GetSUStatusTask;
import com.tzappstudio.autotouchpro.services.task.roottask.RestoreTask;
import com.tzappstudio.autotouchpro.services.task.stringtask.CalculatorMathTask;
import com.tzappstudio.autotouchpro.services.task.stringtask.CheckNetworkConnectivityTask;
import com.tzappstudio.autotouchpro.services.task.stringtask.GetEditTextTask;
import com.tzappstudio.autotouchpro.services.task.stringtask.GetIPTask;
import com.tzappstudio.autotouchpro.services.task.stringtask.GetTimeStringTask;
import com.tzappstudio.autotouchpro.services.task.stringtask.OTPTask;
import com.tzappstudio.autotouchpro.services.task.stringtask.ReadFromClipboardTask;
import com.tzappstudio.autotouchpro.services.task.stringtask.ReadJsonTask;
import com.tzappstudio.autotouchpro.services.task.stringtask.ReplaceTask;
import com.tzappstudio.autotouchpro.services.task.stringtask.StringTask;
import com.tzappstudio.autotouchpro.services.task.variable.GetVarTask;
import com.tzappstudio.autotouchpro.services.task.variable.MultipleLineVarTask;
import com.tzappstudio.autotouchpro.services.task.variable.SetVarTask;
import com.tzappstudio.autotouchpro.services.task.vpntask.SockVPN;
import com.tzappstudio.autotouchpro.services.xml.DumpViewToXmlTask;

import java.util.HashMap;
import java.util.Map;

public class CommandTask extends Command {
    private static final String TAG = CommandTask.class.getSimpleName();
    String taskCommandRaw;
    ICommand commandRaw;
    HashMap<String, String> paramsRaw = new HashMap<>();
    BaseTask task;
    AccessibilityServices services;
    IScreenshotProvider screenShotBinder;
    //static final String regexParseLink = "(?i)\\b((?:[a-z][\\w-]+:(?:\\/{1,3}|[a-z0-9%])|www\\d{0,3}[.]|[a-z0-9.\\-]+[.][a-z]{2,4}\\/)(?:[^\\s()<>]+|\\(([^\\s()<>]+|(\\([^\\s()<>]+\\)))*\\))+(?:\\(([^\\s()<>]+|(\\([^\\s()<>]+\\)))*\\)|[^\\s`!()\\[\\]\\{};:'\".,<>?«»“”‘’]))";
    //static final Pattern pattern = Pattern.compile(regexParseLink, Pattern.MULTILINE);

    public CommandTask(AccessibilityServices services, IScreenshotProvider screenShotBinder, String task) {
        if (task == null) {
            return;
        }
        this.taskCommandRaw = task;
        //final Matcher matcher = pattern.matcher(task.trim());
        //this.taskCommand = task.trim().toLowerCase();
//        try {
//            //skip lowercase in link
//            while (matcher.find()) {
////            System.out.println("Full match: " + matcher.group(0));
//                for (int i = 1; i <= matcher.groupCount(); i++) {
//                    if (matcher.group(i) != null) {
////                    System.out.println("Group " + i + ": " + matcher.group(i));
//                        taskCommand = taskCommand.replaceAll(matcher.group(i).toLowerCase(), matcher.group(i));
//                    }
//                }
//            }
//
//        }catch (Exception e){
//
//        }
//
//        //remove comment code
//        boolean containLink = false;
//        if (taskCommand.contains("http://") || taskCommand.contains("https://") || taskCommand.contains("://")) {
//            containLink = true;
//            taskCommand = taskCommand.replaceAll("://", ":/ /");
//        }
//        if (taskCommand.contains("//")) {
//            int pos = taskCommand.indexOf("//");
//            taskCommand = taskCommand.substring(0, pos);
//            this.taskCommand = taskCommand.trim();
//        }
//        if (containLink) {
//            taskCommand = taskCommand.replaceAll(":/ /", "://");
//        }

        this.screenShotBinder = screenShotBinder;
        this.services = services;
        CommandParser parser = CommandParser.getInstance();

        String parsedVarCommand = taskCommandRaw.trim();
        if (parsedVarCommand.startsWith("//")) {
            return;
        }

        commandRaw = parser.parse(parsedVarCommand);
        if (commandRaw != null) {
            paramsRaw = commandRaw.getParams();
            paramsRaw = convertKeyToLower(paramsRaw);
        }
        parseTask();
    }

    private HashMap<String, String> convertKeyToLower(HashMap<String, String> paramsRaw) {
        HashMap<String, String> rs = new HashMap<>();
        for (Map.Entry<String, String> entry : paramsRaw.entrySet()
        ) {
            rs.put(entry.getKey().toLowerCase(), entry.getValue());
        }
        return rs;
    }


    private void parseTask() {
        if (commandRaw == null) {
            return;
        }
        String commandName = commandRaw.getName().toLowerCase();

        if (commandName.equals("home")) {
            task = new AccessTask(services).goToHome();
        } else if (commandName.equals("back")) {
            task = new AccessTask(services).goToBack();
        } else if (commandName.equals("stop") || commandName.equals("stopall")) {
            String text = paramsRaw.get("text");
            task = new LogToFileTask(services).setContent(text).setThenStop(true);
        } else if (commandName.equals("sleep")) {
            String t = paramsRaw.get("t");
            if (t == null) {
                t = paramsRaw.get("timeout");
            }

            task = new SleepTask(t);
        } else if (commandName.equals("main")) {

        } else if (commandName.equals("wait") || commandName.equals("if")) {
            //wait -text "test text" -timeout 5000 -thenclick true -thenlongclick true -thenswipe "up/down/left/right" -theninput "1234" -var "vartest"

            //wait -text "test text" -timeout 5000 -thenclick true -thenlongclick true -thenswipe "up/down/left/right" -theninput "1234" -varPosX "x" -varPosY "y"

            //wait -text "test text" -timeout 5000 -thenclick true -thenlongclick true -thenswipe "up/down/left/right" -theninput "1234" -varPosStartX "x" -varPosStartY "y" -varPosEndX "x2" -varPosEndY "y2"

            //if -getVar "vartest" -equal null -then "" -else ""
            //wait -edittext "" -pos 0 -timeout 50000 -theninput "randomPhone" //tìm tất cả edittext có thể nhập được, sau đó điền random sđt vào vị trí 0
            //wait -buttontext "" -pos 0 -timeout 50000 -thenClick true
            //wait -id "numberpicker_input" -pos 0 -timeout 5000 -theninput "Feb"
            //wait -id "numberpicker_input" -pos 1 -timeout 5000 -theninput "10"
            //wait -id "numberpicker_input" -pos 2 -timeout 5000 -theninput "1997"
            //wait -image "linkimage/image:data:base64" -timeout 5000 -thenclick true -plusX "-150" -plusY "-150"
            //if -getVar "name" -equal "Long" // -contain "Long"

            //wait -regex {((?:[a-z0-9](?:[a-z0-9-]{0,61}[a-z0-9])?\\.)+[a-z0-9][a-z0-9-]{0,61}[a-z0-9])|((?:(?:https?|ftp|file):\\/\\/|www\\.|ftp\\.)(?:\\([-A-Z0-9+&@#\\/%=~_|$?!:,.]*\\)|[-A-Z0-9+&@#\\/%=~_|$?!:,.])*(?:\\([-A-Z0-9+&@#\\/%=~_|$?!:,.]*\\)|[A-Z0-9+&@#\\/%=~_|$]))|(l\\.php\\?u=)} -thenClick true -pos 0
            //wait -xpath {(  (//*[ contains(@class,'ViewGroup') and .//*[contains(@text,'Sponsored')] and .//*[ contains(@class,'ImageView') and not( contains(@content-desc,'Profile'))] ])[last()] //*[ contains(@class,'ImageView') and not( contains(@content-desc,'Profile'))] )[1]} -timeout 2000 -thenClick true -clickAll true -clickAllDelay 200
            String textParam = paramsRaw.get("text");
            int type = AwaitToTextAppearTask.TYPE_TEXT;

            String idParam = paramsRaw.get("id");
            if (idParam != null && !idParam.isEmpty()) {
                textParam = idParam + "";
            }

            if (textParam == null) {
                textParam = paramsRaw.get("edittext");
                type = AwaitToTextAppearTask.TYPE_EDIT_TEXT;
            }
            if (textParam == null) {
                textParam = paramsRaw.get("button");
                type = AwaitToTextAppearTask.TYPE_BUTTON_TEXT;
            }

            String classNameOfView = paramsRaw.get("view");
            if (classNameOfView != null && !classNameOfView.equals("")) {
                type = AwaitToTextAppearTask.TYPE_OTHER_VIEW_TEXT;
            }
            if (textParam == null) {
                textParam = paramsRaw.get("radiobutton");
                if (textParam != null) {
                    type = AwaitToTextAppearTask.TYPE_OTHER_VIEW_TEXT;
                    classNameOfView = "RadioButton";
                }
            }

            String findByXPath = paramsRaw.get("xpath");
            if (findByXPath != null && !findByXPath.equals("")) {
                type = AwaitToTextAppearTask.TYPE_FIND_WITH_XPATH;
                textParam = paramsRaw.get("xpath");
            }

            String findByTemplate = paramsRaw.get("template");
            if (findByTemplate != null && !findByTemplate.equals("")) {
                type = AwaitToTextAppearTask.TYPE_FIND_WITH_TEMPLATE;
                textParam = paramsRaw.get("template");
            }

            //wait -text 'random -text "Male,Female"'
            textParam = getRandomAndVariableTask(textParam);

            String imageParam = paramsRaw.get("image");
            String t = paramsRaw.get("timeout");
            t = getRandomAndVariableTask(t);
            long time = 1200;
            try {
                time = Long.parseLong(t == null ? "1200" : t);
            } catch (Exception e) {

            } finally {
                if (time < 500) {
                    time = 500;
                }
            }


            String plusX = getRandomAndVariableTask(paramsRaw.get("plusx"));
            String plusY = getRandomAndVariableTask(paramsRaw.get("plusy"));
            int pX = (int) Float.parseFloat(plusX == null ? "0" : plusX);
            int pY = (int) Float.parseFloat(plusY == null ? "0" : plusY);

            String then = getRandomAndVariableTask(paramsRaw.get("thenclick"));
            boolean thenClick = Boolean.parseBoolean(then == null ? "false" : then);
            then = getRandomAndVariableTask(paramsRaw.get("thendoubleclick"));
            boolean thenDoubleClick = Boolean.parseBoolean(then == null ? "false" : then);
            then = getRandomAndVariableTask(paramsRaw.get("thenlongclick"));
            boolean thenLongClick = Boolean.parseBoolean(then == null ? "false" : then);
            then = getRandomAndVariableTask(paramsRaw.get("exactly"));
            boolean exactly = Boolean.parseBoolean(then == null ? "false" : then);
            then = getRandomAndVariableTask(paramsRaw.get("thenscrollto"));
            boolean thenScrollTo = Boolean.parseBoolean(then == null ? "false" : then);

            then = getRandomAndVariableTask(paramsRaw.get("clickall"));
            boolean isClickAllNode = Boolean.parseBoolean(then == null ? "false" : then);

            then = getRandomAndVariableTask(paramsRaw.get("usepx"));
            boolean clickUsePx = Boolean.parseBoolean(then == null ? "false" : then);//plusx plusy la duoc

            then = getRandomAndVariableTask(paramsRaw.get("thenswipe"));
            int swipe = -1;

            if (then != null) {
                if (then.equals("up")) {
                    swipe = 1;
                } else if (then.equals("down")) {
                    swipe = 2;
                } else if (then.equals("left")) {
                    swipe = 3;
                } else if (then.equals("right")) {
                    swipe = 4;
                }
            }
            String thenAction = paramsRaw.get("then");
            String elseAction = paramsRaw.get("else");

            then = paramsRaw.get("theninput");
            if (then == null) {
                then = "";
            }

            String getVar = paramsRaw.get("getvar");
            String equal = paramsRaw.get("equal");
            String contain = paramsRaw.get("contain");

            String file = paramsRaw.get("file");


            String isSequenceInput = paramsRaw.get("slowinput");
            if (isSequenceInput == null) {
                isSequenceInput = paramsRaw.get("sli");
            }
            boolean slowInput = Boolean.parseBoolean(isSequenceInput == null ? "false" : isSequenceInput);

//          task = new AwaitToTextAppearTask.Builder(services, textParam)
//                  .setExactlyText(exactly)
//                  .setTimeOutAwait(time)
//                  .setPositionToExecute(position)
//                  .setThenClick(thenClick)
//                  .setThenDoubleClick(thenDoubleClick)
//                  .setClickPlusPosition(pX, pY)
//                  .setThenLongClick(thenLongClick)
//                  .setThenInput(then)
//                  .setThenSwipe(swipe)
//                  .build();
            String var = paramsRaw.get("var");


            String regex = paramsRaw.get("regex");

            String varPosX = paramsRaw.get("varposx");
            String varPosY = paramsRaw.get("varposy");

            task = new AwaitToTextAppearTask(services, textParam)
                    .setType(type)
                    .setIsClickAllNode(isClickAllNode)
                    .setClickAllNodeDelay(paramsRaw.get("clickalldelay"))
                    .setClickNodeDelay(paramsRaw.get("clickdelay"))
                    .setClickRange(paramsRaw.get("clickrange"))
                    .setFindRange(paramsRaw.get("findrange"))
                    .setTemplateMinMatchingPercent(paramsRaw.get("matchpercent"))
                    .setTemplateResizePercent(paramsRaw.get("resizepercent"))
                    .setTimeInterval(paramsRaw.get("timeinterval"))
                    .setVarPositionX(varPosX).setVarPositionY(varPosY)
                    .setVarPositionStartX(paramsRaw.get("varposstartx"))
                    .setVarPositionStartY(paramsRaw.get("varposstarty"))
                    .setVarPositionEndX(paramsRaw.get("varposendx"))
                    .setVarPositionEndY(paramsRaw.get("varposendy"))
                    .setFindWithRegex(regex)
                    .setFileToCheck(file)
                    .setExactlyText(exactly)
                    .setPosition(paramsRaw.get("pos")).setThenClick(thenClick)
                    .setThenDoubleClick(thenDoubleClick)
                    .setPlusPositionX(pX).setPlusPositionY(pY)
                    .setThenLongClick(thenLongClick)
                    .setThenInput(then)
                    .setThenSwipe(swipe)
                    .setClassNameOfView(classNameOfView)
                    .setThenAction(thenAction).setElseAction(elseAction)
                    .setThenScrollTo(thenScrollTo)
                    .setVar(var)
                    .setGetVarToCompare(getVar)
                    .setEqualTo(equal)
                    .setContainTo(contain)
                    .setSlowInput(slowInput);

            ((AwaitToTextAppearTask) task).setTimeOutAwait(time);

//            if (imageParam != null && !imageParam.isEmpty()) {
//                //final String encodedString = "data:image/jpg;base64, ....";
//                final String pureBase64Encoded = imageParam.substring(imageParam.indexOf(",") + 1);
//                final byte[] decodedBytes = Base64.decode(pureBase64Encoded, Base64.DEFAULT);
//                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
//                task = new AwaitToTemplateAppearTask(services, decodedByte, screenShotBinder);
//            }
        } else if (commandName.equals("swipe")) {
            //swipe -orient up/down/left/right
            //swipe -startx 100 -starty 100 -endx 500 -endy 500
            String orient = getRandomAndVariableTask(paramsRaw.get("orient"));
            if (orient == null || orient.trim().equals("")) {
                orient = getRandomAndVariableTask(paramsRaw.get("o"));
            }
            String timeToSwipe = getRandomAndVariableTask(paramsRaw.get("time"));
            if (timeToSwipe == null || timeToSwipe.trim().equals("")) {
                timeToSwipe = getRandomAndVariableTask(paramsRaw.get("t"));
            }
            timeToSwipe = getRandomAndVariableTask(timeToSwipe);
            long time = 1000;
            try {
                time = Long.parseLong(timeToSwipe == null ? "1000" : timeToSwipe);
            } catch (Exception e) {

            }


            if (orient != null && !orient.isEmpty()) {
                if (orient.equals("up")) {
                    task = new AccessTask(services).setSwipeTop();
                } else if (orient.equals("down")) {
                    task = new AccessTask(services).setSwipeBottom();

                } else if (orient.equals("left")) {
                    task = new AccessTask(services).setSwipeLeft();

                } else if (orient.equals("right")) {
                    task = new AccessTask(services).setSwipeRight();
                }
            } else {
//                String startX = getRandomAndVariableTask(paramsRaw.get("startx"));
//                String startY = getRandomAndVariableTask(paramsRaw.get("starty"));
//                String endX = getRandomAndVariableTask(paramsRaw.get("endx"));
//                String endY = getRandomAndVariableTask(paramsRaw.get("endy"));
//                float sx = Float.parseFloat(startX == null ? "0" : startX);
//                float sy = Float.parseFloat(startY == null ? "0" : startY);
//                float ex = Float.parseFloat(endX == null ? "0" : endX);
//                float ey = Float.parseFloat(endY == null ? "0" : endY);
                String startX = paramsRaw.get("startx");
                String startY = paramsRaw.get("starty");
                String endX = paramsRaw.get("endx");
                String endY = paramsRaw.get("endy");
                task = new AccessTask(services).swipe(startX, startY, endX, endY);
            }
            ((AccessTask) task).setSwipeTime(time);
        } else if (commandName.equals("loop")) {
            //loop -t 4000 //loop -i 30
            // loop -t {getvar -name "i"} //loop -i {getvar -name "i"}
            String t = getRandomAndVariableTask(paramsRaw.get("t"));
            if (t == null) {
                t = getRandomAndVariableTask(paramsRaw.get("timeout"));
            }
            long timeOut = Long.parseLong(t == null ? "0" : t);

            String i = paramsRaw.get("i");
            task = new MainTask(services).setLoopFromDataCSV(paramsRaw.get("incsvdata")).varLoopTime(i);
            task.setRunForever(true);
            if (timeOut > 0) {
                task.setUsingTimeOut(timeOut);
            } else if (i != null) {
                task.setUsingTimeOut(false);
            }

        } else if (commandName.equals("break")) {
            //break
            //break cho loop task
            task = new BreakTask();

        } else if (commandName.equals("recent")) {
            task = new AccessTask(services).goToRecent();
        } else if (commandName.equals("openapp")) {
            String package_name = getRandomAndVariableTask(paramsRaw.get("p"));
            task = new AccessTask(services).openApp(package_name == null ? "" : package_name);
        } else if (commandName.equals("opennotification") || commandName.equals("notification")) {
            task = new AccessTask(services).goToNotification();
        } else if (commandName.equals("click")) {
            //click -x 500 -y 100
            //click -xy '500,199'
            String xy = paramsRaw.get("xy");
            if (xy != null) {
                try {
                    String x = xy.split(",")[0];
                    String y = xy.split(",")[1];
                    task = new AccessTask(services).click(x, y);
                } catch (Exception e) {
                    task = new AccessTask(services).click(paramsRaw.get("x"), paramsRaw.get("y"));
                }
            } else {
                task = new AccessTask(services).click(paramsRaw.get("x"), paramsRaw.get("y"));
            }

        } else if (commandName.equals("doubleclick")) {
            //click -x 500 -y 100
            task = new AccessTask(services).doubleClick(paramsRaw.get("x"), paramsRaw.get("y"));
        } else if (commandName.equals("longclick")) {
            //longclick -x 500 -y 100
            task = new AccessTask(services).longClick(paramsRaw.get("x"), paramsRaw.get("y"));
        } else if (commandName.equals("quicksetting")) {
            task = new AccessTask(services).goToQuickSettings();
        } else if (commandName.equals("opendetailapp")) {
            String package_name_detail = getRandomAndVariableTask(paramsRaw.get("p"));
            task = new AccessTask(services).openDetailSetting(package_name_detail);
        } else if (commandName.equals("getotp")) {
            //getOTP -key P6NTOT7IVUCVQEAKGA2CWWO3SD5HXJ45
            //getOTP -key paste
            String key = paramsRaw.get("key");
            key = getStringTask(key);
            task = new OTPTask(key);
        } else if (commandName.equals("openfacebookpost")) {
            //OpenFacebookPost -p com.facebook.katana -id 949449446412651
            String p = getRandomAndVariableTask(paramsRaw.get("p"));
            String id = getRandomAndVariableTask(paramsRaw.get("id"));
            task = new OpenFacebookPost(services, p, id);
        } else if (commandName.equals("opendeeplink")) {
            //OpenDeepLink -p com.facebook.katana -url "https://vt.tiktok.com/ZSXCh2Hb"
            String p = paramsRaw.get("p");
            String url = paramsRaw.get("url");
            task = new OpenDeepLinkTask(services, p, url);
        } else if (commandName.equals("clickcenter")) {
            task = new AccessTask(services).clickCenter();
        } else if (commandName.equals("startproxy")) {
            String hostPort = getRandomAndVariableTask(paramsRaw.get("ip"));
            task = new AccessTask(services).changeProxy(hostPort);
        } else if (commandName.equals("stopproxy")) {
            task = new AccessTask(services).stopProxy();
        } else if (commandName.equals("get")) {
            // get -url "https://api.example.com/api/v2/order/request?apiKey=maaH76Urmo05DH2GLDAftyaPLyiLLlomhUztTcgmSqkv&serviceProviderId=22" -field "id" -var "id_phone"
            // get -localpath "/AutoTouchPro/config.json" -field "deviceName" -var "id_phone"
            String url = paramsRaw.get("url");
            String localPath = paramsRaw.get("localpath");
            String field = paramsRaw.get("f");
            if (field == null || field.isEmpty()) {
                field = paramsRaw.get("field");
            }
            String var = paramsRaw.get("var");


            String regex = getRandomAndVariableTask(paramsRaw.get("regex"));
            if (regex == null || regex.equals("")) {
                regex = getRandomAndVariableTask(paramsRaw.get("reg"));
            }

            String posRegex = getRandomAndVariableTask(paramsRaw.get("regexpos"));
            if (posRegex == null || posRegex.equals("")) {
                posRegex = getRandomAndVariableTask(paramsRaw.get("regp"));
            }
            int regPos = Integer.parseInt(posRegex == null ? "1" : posRegex);

            String posStart = getRandomAndVariableTask(paramsRaw.get("startat"));
            if (posStart == null || posStart.equals("")) {
                posStart = getRandomAndVariableTask(paramsRaw.get("start"));
            }
            int startAt = Integer.parseInt(posStart == null ? "-1" : posStart);

            String posEnd = getRandomAndVariableTask(paramsRaw.get("endat"));
            if (posEnd == null || posEnd.equals("")) {
                posEnd = getRandomAndVariableTask(paramsRaw.get("end"));
            }
            int endAt = Integer.parseInt(posEnd == null ? "-1" : posEnd);

            task = new GetJsonFromUrlTask(url, field).setVar(var)
                    .setRegex(regex)
                    .setLocalJsonPath(localPath)
                    .setPosStart(startAt)
                    .setPosEnd(endAt)
                    .setPosRegex(regPos);


        } else if (commandName.equals("replace")) {
            String text = paramsRaw.get("text");
            String replace = paramsRaw.get("replace");
            String to = paramsRaw.get("to");
            task = new ReplaceTask().setText(text).setReplace(replace).setTo(to);
        } else if (commandName.equals("getedittext") || commandName.equals("getalledittext")) {
            String pos = getRandomAndVariableTask(paramsRaw.get("pos"));
            pos = getRandomAndVariableTask(pos);
            int position = Integer.parseInt(pos == null ? "-1" : pos);
            task = new GetEditTextTask(services).setPosition(position);
        } else if (commandName.equals("getcurrenttime")) {
            String format = paramsRaw.get("format");
            task = new GetTimeStringTask().setFormat(format);
        } else if (commandName.equals("reademail") || commandName.equals("readmail")) {
            //readMail -user "viktorblinovkv@hotmail.com" -pass "vgyK31s6ktg"  -contain "mã xác nhận" -limit 10 -regex "[0-9]{5}" -regexPos 1
            String user = paramsRaw.get("user");
            if (user == null || user.equals("")) {
                user = paramsRaw.get("u");
            }
            String pass = paramsRaw.get("password");
            if (pass == null || pass.equals("")) {
                pass = paramsRaw.get("pass");
            }

            String portStr = getRandomAndVariableTask(paramsRaw.get("p"));
            if (portStr == null || portStr.equals("")) {
                portStr = getRandomAndVariableTask(paramsRaw.get("port"));
            }
            int port = Integer.parseInt(portStr == null ? String.valueOf(ReadMailTask.PORT_DEFAULT) : portStr);

            String regex = getRandomAndVariableTask(paramsRaw.get("regex"));
            if (regex == null || regex.equals("")) {
                regex = getRandomAndVariableTask(paramsRaw.get("reg"));
            }

            String posRegex = getRandomAndVariableTask(paramsRaw.get("regexpos"));
            if (posRegex == null || posRegex.equals("")) {
                posRegex = getRandomAndVariableTask(paramsRaw.get("regp"));
            }

            String posGroup = getRandomAndVariableTask(paramsRaw.get("grouppos"));
            if (posGroup == null || posGroup.equals("")) {
                posGroup = getRandomAndVariableTask(paramsRaw.get("grp"));
            }

            int regPos = Integer.parseInt(posRegex == null ? "1" : posRegex);
            int groupPos = Integer.parseInt(posGroup == null ? "0" : posGroup);

            String posStart = getRandomAndVariableTask(paramsRaw.get("startat"));
            if (posStart == null || posStart.equals("")) {
                posStart = getRandomAndVariableTask(paramsRaw.get("start"));
            }
            int startAt = Integer.parseInt(posStart == null ? "-1" : posStart);

            String posEnd = getRandomAndVariableTask(paramsRaw.get("endat"));
            if (posEnd == null || posEnd.equals("")) {
                posEnd = getRandomAndVariableTask(paramsRaw.get("end"));
            }
            int endAt = Integer.parseInt(posEnd == null ? "-1" : posEnd);

            String limit = getRandomAndVariableTask(paramsRaw.get("limit"));
            if (limit == null || limit.equals("")) {
                limit = getRandomAndVariableTask(paramsRaw.get("lm"));
            }
            int lm = Integer.parseInt(limit == null ? "100" : limit);

            String contain = paramsRaw.get("contain");
            if (contain == null || contain.equals("")) {
                contain = paramsRaw.get("ct");
            }

            String host = paramsRaw.get("host");
            if (host == null || host.equals("")) {
                host = paramsRaw.get("h");
            }
            task = new ReadMailTask(user, pass)
                    .setHost(host).setPort(port)
                    .setRegex(regex)
                    .setPosRegex(regPos)
                    .setPosGroup(groupPos)
                    .setContain(contain)
                    .setLimit(lm)
                    .setPosStart(startAt)
                    .setPosEnd(endAt);
            task.setVarToSave(paramsRaw.get("var"));
        } else if (commandName.equals("randomnumber") || commandName.equals("random")) {
            String from = paramsRaw.get("from");
            if (from == null || from.trim().equals("")) {
                from = paramsRaw.get("f");
            }
            String to = paramsRaw.get("to");
            if (to == null || to.trim().equals("")) {
                to = paramsRaw.get("t");
            }
            String text = paramsRaw.get("text");
            if (text != null && !text.equals("")) {
                task = new TextRandomTask().setListData(text);
            } else {
                task = new NumberRandomTask().setFrom(from).setTo(to);
            }

        } else if (commandName.equals("randomphone")) {
            task = new PhoneRandomTask();
        } else if (commandName.equals("randomemail")) {
            String name = paramsRaw.get("name");
            String domain = paramsRaw.get("domain");
            task = new RandomEmailTask().setName(name).setDomain(domain);
        } else if (commandName.equals("randomfirstname")) {
            task = new FirstNameRandomTask();
        } else if (commandName.equals("randomlastname")) {
            task = new LastNameRandomTask();
        } else if (commandName.equals("randompassword")) {
            task = new RandomPassWordTask();
        } else if (commandName.equals("paste") || commandName.equals("readfromclipboard")) {
            task = new ReadFromClipboardTask(services);
        } else if (commandName.equals("log")) {
            /*get -localPath '/sdcard/AutoTouchPro/config.json' -var json
                //-- append ra file log
                log -text 'getVar -name json' -file "/sdcard/test.log"
                //ghi đè ra file
                log -text 'getVar -name json' -file "/sdcard/test.json" -overWrite true
              */
            String content = paramsRaw.get("text");
            //content = getStringTask(content);
            String file = paramsRaw.get("file");
            boolean isNewLine = Boolean.parseBoolean(getRandomAndVariableTask(paramsRaw.get("ln") == null ? "true" : getRandomAndVariableTask(paramsRaw.get("ln"))));
            task = new LogToFileTask(services)
                    .setContent(content)
                    .setNewLine(isNewLine).setFile(file);
        } else if (commandName.equals("json")) {
            //json -file "test" -field "current" -value "processing" -rewrite true//ghi de
            String field = paramsRaw.get("field");
            if (field == null) {
                field = paramsRaw.get("f");
            }
            String value = paramsRaw.get("value");
            if (value == null) {
                value = paramsRaw.get("v");
            }
            String file = paramsRaw.get("file");
            if (file != null) {
                String rw = paramsRaw.get("rewrite");
                boolean rewrite = Boolean.parseBoolean(rw == null ? "false" : rw);
                task = new JsonToFileTask()
                        .setField(field)
                        .setRewrite(rewrite)
                        .setFile(file).setValue(value);
            }

        } else if (commandName.equals("setinfo")) {
            //setInfo -file "test" -p "com.facebook.katana" -field "current" -value "processing" /thêm thông tin vào các package
            // setInfo -folder /AutoTouchPro/backup/com.facebook.katana/ -key 1012423940234 -field uid -value 1012423940234
            // setInfo -folder "/AutoTouchPro/backup/com.facebook.katana/" -key "1012423940234" -field "2fa" -value "ABC DEF GHE FGH"
            String pk = paramsRaw.get("package");
            if (pk == null) {
                pk = paramsRaw.get("p");
            }

            String path = paramsRaw.get("path");
            if (path == null) {
                path = paramsRaw.get("folder");
            }
            String dataKey = paramsRaw.get("key");
            if (dataKey == null) {
                dataKey = paramsRaw.get("k");
            }

            String field = paramsRaw.get("field");
            if (field == null) {
                field = paramsRaw.get("f");
            }
            String value = paramsRaw.get("value");
            if (value == null) {
                value = paramsRaw.get("v");
            }
            String file = paramsRaw.get("file");

            task = new GetAllInfoTask(services)
                    .setPath(path)
                    .setDataKey(dataKey)
                    .setPackageName(pk)
                    .setField(field)
                    .setFile(file)
                    .setValue(value);

        } else if (commandName.equals("getvar")) {
            String var = paramsRaw.get("var");
            if (var == null) {
                var = paramsRaw.get("name");
            }
            task = new GetVarTask().setVarName(var);
        } else if (commandName.equals("var")) {
            //var -name "image" -value 'getImage -startX 100 -startY 150 -endX 500 -endY 550';
            String name = paramsRaw.get("name");
            String value = paramsRaw.get("value");
            task = new SetVarTask(services).setName(name).setValue(value);
        } else if (commandName.equals("backupapp")) {
            //backupapp -p "com.facebook.katana" -to 'AutoTouchPro/backup' -name 'test.bk' -backupCache true;
            String packageName = paramsRaw.get("p");
            String folderToBk = paramsRaw.get("to");
            String fileNameToBk = paramsRaw.get("name");
            String isFullBackup = paramsRaw.get("backupcache");
            task = new BackupTask().setPackageToBackup(packageName).setFolderToBackUp(folderToBk).setFileNameToBackup(fileNameToBk).setFullBackup(isFullBackup);

        } else if (commandName.equals("restoreapp")) {
            //restoreapp -p "com.facebook.katana" -from 'AutoTouchPro/backup' -name 'test.bk';
            String packageName = paramsRaw.get("p");
            String fromFolder = paramsRaw.get("from");
            String fileNameToRestore = paramsRaw.get("name");
            task = new RestoreTask().setPackageToRestore(packageName).setFromFolder(fromFolder).setFileNameToRestore(fileNameToRestore);

        } else if (commandName.equals("cleardata")) {
            //clearData -p "com.facebook.katana"
            String packageName = paramsRaw.get("p");
            task = new ClearDataTask().setPackageToClear(packageName);

        } else if (commandName.equals("getimage")) {

            String startX = getRandomAndVariableTask(paramsRaw.get("startx"));
            String startY = getRandomAndVariableTask(paramsRaw.get("starty"));
            String endX = getRandomAndVariableTask(paramsRaw.get("endx"));
            String endY = getRandomAndVariableTask(paramsRaw.get("endy"));
            int sx = (int) Float.parseFloat(startX == null ? "0" : startX);
            int sy = (int) Float.parseFloat(startY == null ? "0" : startY);
            int ex = (int) Float.parseFloat(endX == null ? "0" : endX);
            int ey = (int) Float.parseFloat(endY == null ? "0" : endY);
            Rect rect = new Rect(sx, sy, ex, ey);
            task = new GetImageTask().setService(services).setRect(rect);
            ((GetImageTask) task).setTimeOutAwait(20000);
        } else if (commandName.equals("getgifimage")) {
            //getGIFImage -frame 30 -time 3000 -name "test.gif" -startX 100 -startY 150 -endX 500 -endY 550

            String startX = paramsRaw.get("startx");
            String startY = paramsRaw.get("starty");
            String endX = paramsRaw.get("endx");
            String endY = paramsRaw.get("endy");

            String name = paramsRaw.get("name");
            String num = getRandomAndVariableTask(paramsRaw.get("frame"));
            int numberFrame = Integer.parseInt(num == null ? "20" : num);
            String timeP = getRandomAndVariableTask(paramsRaw.get("time"));
            int time = Integer.parseInt(timeP == null ? "2000" : timeP);

            String maxSize = getRandomAndVariableTask(paramsRaw.get("size"));
            int size = Integer.parseInt(maxSize == null ? "180" : maxSize);

            task = new GetGifImageTask()
                    .setService(services)
                    .setRect(startX, startY, endX, endY)
                    .setName(name)
                    .setNumberFrame(numberFrame)
                    .setTime(time)
                    .setMaxSize(size);
        } else if (commandName.equals("toast")) {
            String text = paramsRaw.get("text");
            task = new ToastTask().setText(text).setEnableSound(paramsRaw.get("sound"));

        } else if (commandName.equals("changeinfo")) {
            task = new ChangeInfoTask();

        } else if (commandName.equals("deletefile")) {
            //deleteFile -path '/AutoTouchPro/backup/config.bak'
            //deleteFile -path '/storage/emulated/0/AutoTouchPro/backup/config.bak'
            String filePath = paramsRaw.get("path");
            task = new DeleteFileTask(filePath);
        } else if (commandName.equals("adbshell")) {
            //adbshell -su true -command {rm -r "/storage/emulated/0/AutoTouchPro/backup/config.bak"}
            String command = paramsRaw.get("command");
            String useSu = paramsRaw.get("su");
            String waitToFinish = paramsRaw.get("wait");
            boolean isUseSu = useSu != null && Boolean.parseBoolean(useSu);
            boolean wait = waitToFinish == null || Boolean.parseBoolean(waitToFinish);
            task = new ShellTask(command).setUseSu(isUseSu).setWaitToFinish(wait);
        } else if (commandName.equals("screenshot")) {
            //screenshot -to /storage/emulated/0/testscreenshot.jpg -maxSize 900

            //screenshot -to /storage/emulated/0/testscreenshot.jpg -startX 100 -startY 200 -endX 300 -endY 500 -maxSize 1920

            String saveTo = paramsRaw.get("to");

            String startX = paramsRaw.get("startx");
            String startY = paramsRaw.get("starty");
            String endX = paramsRaw.get("endx");
            String endY = paramsRaw.get("endy");

            task = new ScreenshotTask().setSaveTo(saveTo).setRect(startX, startY, endX, endY);
        } else if (commandName.equals("uploadfile")) {
            //UploadFile -file 'screenshot -to /storage/emulated/0/testscreenshot.jpg' -url "http://post.lalasoft.vn/data_post_image/upload.php" -field "fileToUpload"
            task = new UploadFile()
                    .setFileToUpload(paramsRaw.get("file"))
                    .setField(paramsRaw.get("field"))
                    .setUrl(paramsRaw.get("url"));
        } else if (commandName.equals("gettimestamp")) {
            task = new GetTimeStampTask();
        } else if (commandName.equals("loopcontinue")) {
            task = new LoopContinueTask();
        } else if (commandName.equals("csvdatarow") || commandName.equals("datarow")) {
            //sửa data tại row hiện tại của loop với col và giá trị truyền vào, col có thể là vị trí hoặc header name
            //		csvDataRow -col 2 -setText "running"
            //		csvDataRow -col "phone" -setText "runningphone"
            task = new DataRowTask().setCol(paramsRaw.get("col")).setSetText(paramsRaw.get("settext"));
        } else if (commandName.equals("calculator") || commandName.equals("calc")) {
            //tính toán biểu thức theo javascript
            //calc -math "10+5-2" -var "test"
            //hoặc
            //calculator -math "(10+5)^2" -var "test2"
            task = new CalculatorMathTask().setMath(paramsRaw.get("math"));
        } else if (commandName.equals("resizeimage")) {
            //Resize 1 ảnh với kích thước max pixel, giữ nguyên ratio
            //resizeImage -path /storage/emulated/0/testscreenshot_not_resize.jpg -maxSize 500 -to /storage/emulated/0/resized.jpg
            task = new ResizeImageTask().setPath(paramsRaw.get("path")).setSaveTo(paramsRaw.get("to")).setMaxSize(paramsRaw.get("maxsize"));
        } else if (commandName.equals("readjson")) {
            //ReadJson -fromVar "test" -field "exist" -var "result"
            task = new ReadJsonTask().setJson(paramsRaw.get("fromvar")).setField(paramsRaw.get("field"));
        } else if (commandName.equals("downloadfile")) {
            //DownloadFile -url "http://file.lalasoft.vn/data1.csv" -to "/sdcard/AutoTouchPro/test.csv"
            task = new DownloadFileTask().setUrl(paramsRaw.get("url")).setSaveTo(paramsRaw.get("to"));
        } else if (commandName.equals("dumpview")) {
            //dumpview
            task = new DumpViewToXmlTask();
        } else if (commandName.equals("testcrash")) {
            throw new RuntimeException("Test Crash"); // Force a crash
        } else if (commandName.equals("submitdata")) {
             /*
            SubmitData -method "GET"  -url "http://graph.facebook.com/me?access_token=EAGG"  -header "Host: graph.facebook.com | Accept: text/html" -splitBy "|"

            VD2  post form-data:
            SubmitData -method "POST"  -url "http://www.fb.com/login" -header "Host: www.fb.com | Content-Type: application/x-www-form-urlencoded | User-Agent:  Mozilla/5.0 (Windows NT 6.1; Win64; x64) Chrome 89  | Barer-Token: EAAG | Cookie: fbdtsg=fk0002333, gfb=0, _gaid=ok00933" -splitBy "|" -body "user_name=test&pass=testpass"

            VD3 post json :
            SubmitData -method "POST"  -url "http://graph.fb.com/feed" -header "Host: graph.fb.com | Content-Type: application/json | User-Agent:  Mozilla/5.0 (Windows NT 6.1; Win64; x64) Chrome 89" -splitBy "|" -body `{‘access_token’ : ‘EAAGGKJJLGdgfGOIPKZ’ , ‘user_id’ : ‘100000390344256’}`

             */
            task = new SubmitDataTask()
                    .setMethod(paramsRaw.get("method"))
                    .setUrl(paramsRaw.get("url"))
                    .setHeader(paramsRaw.get("header"))
                    .setBody(paramsRaw.get("body"))
                    .setSplitBy(paramsRaw.get("splitby"));
        } else if (commandName.equals("checknetwork")) {
            task = new CheckNetworkConnectivityTask();
        } else if (commandName.equals("getipaddress")) {
            task = new GetIPTask();
        } else if (commandName.equals("randomuseragent")) {
            task = new UserAgentRandomTask();
        } else if (commandName.equals("runfromfile")) {
            //RunFromFile -path "/sdcard/AutoTouchPro/downloaded_file.atp" -onOtherThread true
            //chạy 1 file từ 1 file khác
            task = new RunFromFileTask();
        } else if (commandName.equals("installapk")) {
            //InstallAPK -p "com.facebook.katana" -v "1.0.23.123" -url "https://file.lalasoft.vn/test.apk"
            //yêu cầu máy root và cài app AutoPhoneUpdater là system app

            /*
            //demo check
            loop -i 1
                InstallAPK -p "com.tzapp.dialer" -v "5.14.1" -url "http://up.lalasoft.vn/upload/dialer-core-debug.apk"
                getVersionApp -p "com.tzapp.dialer" -var "v"
                toast -text 'getVar -name v'
                sleep -t 5000

                wait -text "Latest" -timeout 10000 -then {toast -text "đã Latest"}
                wait -text "Latest" -timeout 2000 -then {break}

                wait -text "Installed" -timeout 40000 -then {getVersionApp -p "com.tzapp.dialer" -var "v"} -else {break}
                toast -text 'getVar -name v'
            endLoop
            * */

            task = new InstallAPKViaUpdater();
        } else if (commandName.equals("uninstallapk")) {
            //UninstallAPK -p "com.facebook.katana"
            //todo đang lỗi không uninstall được
            task = new UninstallAPKViaUpdater();
        } else if (commandName.equals("getversionapp")) {
            //getVersionApp -p "com.facebook.katana"
            task = new GetVersionAppByPackage();
        } else if (commandName.equals("installapkviaintent")) {
            //InstallAPKViaIntent -apkPath "/sdcard/AutoPhoneUpdater.apk"
            //mục đích: update apk cho app system AutoPhoneUpdater.apk
            task = new InstallAPKViaIntent();
        } else if (commandName.equals("getrunningapp")) {
            //GetRunningApp -var running
            task = new GetRunningAppTask();
        } else if (commandName.equals("stopapp")) {
            //StopApp -p "com.facebook.katana,us.zoom.videomeetings,com.shopee.vn" -skip "de.clemensbartz.android.launcher,com.android.adbkeyboard,org.meowcat.edxposed.manager"  -splitBy ","
            task = new StopAppTask();
        } else if (commandName.equals("getcputemperature")) {
            //GetCPUTemperature -var temp
            task = new GetCPUTemperatureTask();
        } else if (commandName.equals("getdeviceserial")) {
            //GetDeviceSerial -var serial
            task = new GetSerialTask();
        } else if (commandName.equals("turnonflash")) {
            //TurnOnFlash
            task = new TurnOnFlashTask();
        } else if (commandName.equals("turnoffflash")) {
            //TurnOffFlash
            task = new TurnOffFlashTask();
        } else if (commandName.equals("UpdateConfigFile".toLowerCase())) {
            //demo
            /*
            sequence
                UpdateConfigFile -k "temperature" -v "getCPUTemperature"
                UpdateConfigFile -key "customJson" -value `{"a":"b","arr":[{"obj1":"test obj1"},{"obj2":"test obj2"}]}`
                UpdateConfigFile -key "customJsonArray" -value `[{"obj3":"test obj3"},{"obj4":"test obj4"}]`

                //toast -text {get -localPath "/AutoTouchPro/config.json" -field "customJson.arr[1].obj2" }
                toast -text {get -localPath "/AutoTouchPro/config.json" -field "customJsonArray[1].obj4" }
            endSequence
             */
            if (paramsRaw.get("file") != null) {
                task = new UpdateConfigFileTask();
            } else {
                task = new UpdateDBConfigTask();
            }

        } else if (commandName.equals("CheckUsbDebuggingMode".toLowerCase())) {
            //demo
            /*
            sequence
                CheckUsbDebuggingMode -var status

                //status trả về
                // "connected": cả developer mode và usb đang enable
                // "not_connected": chỉ developer mode enabe còn usb thì không
                // null : cả developer mode và usb đều disable

                toast -text {getVar -name "status"}
            endSequence
             */
            task = new CheckUsbDebuggingModeTask();
        } else if (commandName.equals("stop_button".toLowerCase())) {
            //stop button task
            task = new StopButtonTask();
        } else if (commandName.equals("run_button".toLowerCase())) {
            //run button task
            task = new RunButtonTask();
        } else if (commandName.equals("force_stop_and_run_button".toLowerCase())) {
            //run button task
            task = new ForceStopAndRunButtonTask();
        } else if (commandName.equals("SockVPN".toLowerCase())) {
            //SockVPN -input "ip:port:user:pass"
            task = new SockVPN();
        } else if (commandName.equals("OpenFreeBrowser".toLowerCase())) {
            //OpenFreeBrowser -deviceCode "001" -trackerId "6415491f475ec30ce4c0f105"
            task = new OpenFreeBrowserTask();
        } else if (commandName.equals("GetLocalIPWifi".toLowerCase())) {
            //GetLocalIPWifi -var "ip"
            task = new GetLocalIPWifiTask();
        } else if (commandName.equals("GetStatusDataConnection".toLowerCase())) {
            //GetStatusDataConnection -var "type"
            task = new GetStatusDataConnectionTask();
        } else if (commandName.equals("GetSignalStrengthDataNetwork".toLowerCase())) {
            //GetSignalStrengthDataNetwork -var "signal"
            task = new GetSignalStrengthDataNetworkTask();
        } else if (commandName.equals("GetIPPublic".toLowerCase())) {
            //GetIPPublic -var "ip"
            task = new GetIPPublicTask();
        } else if (commandName.equals("Force4GLTE".toLowerCase())) {
            //Force4GLTE
            task = new Force4GLTETask();
        } else if (commandName.equals("Force3GNetwork".toLowerCase())) {
            //Force3GNetwork
            task = new Force3GNetworkTask();
        } else if (commandName.equals("ScreenshotNonRoot".toLowerCase())) {
            //ScreenshotNonRootTask -to "/sdcard/test_non_root.jpg"
            task = new ScreenshotNonRootTask();
            ((AwaitAndTimeOutBaseTask) task).setTimeOutAwait(10000);
        } else if (commandName.equals("GetXposedStatus".toLowerCase())) {
            //GetXposedStatus -var rs
            task = new GetXposedStatusTask();

        } else if (commandName.equals("GetRootStatus".toLowerCase())) {
            //GetRootStatus -var rs
            task = new GetSUStatusTask();

        } else if (commandName.equals("GetRootStatus".toLowerCase())) {
            //GetRootStatus -var rs
            task = new GetSUStatusTask();

        } else if (commandName.equals("GetRunButtonStatus".toLowerCase())) {
            //GetRunButtonStatus -var rs
            //toast -text 'getVar -name rs'
            //show, hide
            task = new GetRunButtonStatusTask();

        } else if (commandName.equals("OpenAirPlaneSettings".toLowerCase())) {
            //OpenAirPlaneSettings
            task = new OpenAirPlaneSettingsTask();

        } else if (commandName.equals("OpenIntentSettings".toLowerCase())) {
            //OpenIntentSettings -name 'android.settings.AIRPLANE_MODE_SETTINGS'
            //OpenIntentSettings -name 'android.settings.BLUETOOTH_SETTINGS'
            //see https://android.googlesource.com/platform/frameworks/base/+/master/core/java/android/provider/Settings.java
            //with ACTIVITY_INTENT_ACTION annotations only
            task = new OpenIntentSettingsTask();

        } else if (commandName.equals("RemoteADBShell".toLowerCase())) {
            //RemoteADBShell -port 5555 -command "ls -l"
            task = new RemoteADBShellTask();

        } else if (commandName.equals("EncryptFile".toLowerCase())) {
            //EncryptFile -path "/sdcard/AutoTouchPro/test.atp"
            //EncryptFile -path "/sdcard/AutoTouchPro/test.atp" -output "/sdcard/AutoTouchPro/test.dll"

            task = new EncryptFileTask();

        } else if (commandName.equals("RunDLL".toLowerCase())) {
            //RunDLL -path "/sdcard/AutoTouchPro/downloaded_file.dll" -onOtherThread true
            //chạy 1 file DLL từ 1 file khác hoặc bỏ nếu muốn chạy tuần tự
            task = new RunFromFileTask();
            ((RunFromFileTask) task).setIsDLL(true);
        } else if (commandName.equals("MultipleLineVar".toLowerCase()) || commandName.equals("MVar".toLowerCase())) {
            //
            task = new MultipleLineVarTask();
        } else if (commandName.equals("AddContactTask".toLowerCase()) || commandName.equals("AddContact".toLowerCase())) {
            //AddContact -name "test name" -number "012345"
            task = new AddContactTask();
        } else if (commandName.equals("ImportContact".toLowerCase()) || commandName.equals("ImportContactTask".toLowerCase())) {
            //ImportContact -file "/sdcard/list.csv" //file gồm 2 cột, cột đầu là tên, cột thứ 2 là number
            task = new ImportContactFromCsvTask();
        } else if (commandName.equals("CopyToClipboard".toLowerCase()) || commandName.equals("CopyToClipboardTask".toLowerCase())) {
            //CopyToClipboard -text "test tiếng việt"
            //RemoteADBShell -command 'input keyevent 279' //action paste cho trường hợp không dùng find được edit text
            //ClearClipboard //đang không hoạt động
            task = new CopyToClipboardTask();
        } else {
            String classNameCommand = commandRaw.getName() + "Task";
            try {


                /*
                //db task
                // - các file config,static a hạn chế ghi (1 ngày ghi 1 lần hoặc có update gì mới ghi), chỉ cho đọc thôi
                // - còn muốn ghi biến gì thay đổi nhiều, config gì thay đổi em sẽ cho ghi vào database, đọc những biến hay thay đổi này từ database
                //xoá sạch DB config json ,sử dụng khi quá rác
                DeleteDBConfig

                //lấy full string json trong DB, sử dụng để check
                GetDBConfigJson -var json
                log -text 'getVar -name json' -file "/sdcard/test.json"

                //ghi vào DB json config với key, nếu chưa có sẽ insert, có rồi sẽ update
                UpdateDBConfig -key "currentTime" -value "GetCurrentTime"
                UpdateDBConfig -key "deviceID" -value "GetDeviceSerial"


                //lấy trường deviceID trong DB json
                GetDBConfig -key "deviceID" -var dvid
                toast -text 'getVar -name dvid'

                //ghi đè full json vào DB, các trường khác sẽ mất, cần đúng cú pháp json thì mới ghi được, kết quả lệnh này trả về true nếu ghi thành công, fasle ngược lại
                UpdateDBConfigJson -json `{"testKey":"testValue"}`

                //có thể get json từ local path hoặc từ internet rồi ghi vào


                 */

                String className = "com.tzappstudio.autotouchpro.services.task.reflection.dbtask." + classNameCommand; // the fully qualified name of the class
                Class<?> clazz = Class.forName(className); // get a reference to the Class object
                task = (BaseTask) clazz.getDeclaredConstructor().newInstance(); // create a new instance of the class using reflection


            } catch (Exception e) {
                //Thêm task mới không cần khai báoở CommandTask, với điều kiện thuộc trong package reflection (không bị guard rule minify)
                task = getReflectionTask(classNameCommand);
            }
        }


        if (task instanceof StringTask) {
            ((StringTask) task).setVarToSave(paramsRaw.get("var"));
        }

        if (task != null) {
            task.setFullCommandRaw(taskCommandRaw);
            task.setParamRaw(paramsRaw);
            task.setVarToSave(paramsRaw.get("var"));
            boolean skipInlineVar = false;
            try {
                skipInlineVar = Boolean.parseBoolean(paramsRaw.get("skipinlinevar"));
            } catch (Exception ignored) {
            }
            task.setSkipInlineVar(skipInlineVar);
            task.setServices(services);
        }
    }

    private BaseTask getReflectionTask(String classNameCommand) {
        String[] reflectPackages = new String[]{
                "dbtask",
                "remote_shell",
                "common"
        };

        for (String subPackage : reflectPackages) {
            String className = "com.tzappstudio.autotouchpro.services.task.reflection." + subPackage + "." + classNameCommand; // the fully qualified name of the class
            try {
                Class<?> clazz = Class.forName(className); // get a reference to the Class object
                task = (BaseTask) clazz.getDeclaredConstructor().newInstance(); // create a new instance of the class using reflection
                return task;
            } catch (Exception ignored) {
            }
        }
        return null;
    }

    private String getStringTask(String task) {
        BaseTask taskExe = new CommandTask(services, null, task).getTask();
        if (taskExe instanceof StringTask) {
            String taskParse = ((StringTask) taskExe).run().toString();
            if (taskParse != null) {
                Log.i(TAG, "getStringTask: " + taskParse);
                return taskParse;
            }

        }
        return task;
    }

    private String getRandomAndVariableTask(String task) {
        BaseTask taskExe = new CommandTask(services, null, task).getTask();
        if (taskExe instanceof RandomTask) {
            Object taskParse = ((RandomTask) taskExe).run();
            if (taskParse != null) {
                Log.i(TAG, "getRandomTask: " + taskParse.toString());
                return taskParse.toString();
            }

        } else if (taskExe instanceof GetVarTask) {
            Object taskParse = ((GetVarTask) taskExe).run();
            if (taskParse != null) {
                Log.i(TAG, "GetVarTask: " + taskParse);
                return taskParse.toString();
            }
        }
        return task;
    }

    public Object getVariableTask(String task) {
        BaseTask taskExe = new CommandTask(services, null, task).getTask();
        if (taskExe instanceof GetVarTask) {
            Object taskParse = ((GetVarTask) taskExe).run();
            if (taskParse != null) {
                Log.i(TAG, "GetVarTask: " + taskParse.getClass().getName());
                return taskParse;
            }
        }
        return task;
    }

    public BaseTask getTask() {
        return task;
    }

    @Override
    public void exec() {

    }
}
