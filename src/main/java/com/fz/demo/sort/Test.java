package com.fz.demo.sort;

import net.dongliu.apk.parser.ApkFile;
import net.dongliu.apk.parser.bean.AdaptiveIcon;
import net.dongliu.apk.parser.bean.ApkMeta;
import net.dongliu.apk.parser.bean.Icon;
import net.dongliu.apk.parser.bean.IconFace;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
    int arr[] = new int[10];

    public static void main(String args[]) {
//		new Thread(new Runnable() {
//
//			@Override
//			public void run() {
//				List<Integer> array = new ArrayList<>();
//				for (int i = 0; i < 80000; i++) {
//					Random random = new Random();
//					array.add(random.nextInt(80000));
//				}
//				int length = array.size();
//				Integer a[] = array.toArray(new Integer[length]);
//				long startTime = System.currentTimeMillis();
//				QuickSort.<Integer>quickSort(a, 0, a.length - 1);
//				System.out.println("排序结果：" + Arrays.toString(a));
//				System.out.println("快速耗时：" + (System.currentTimeMillis() - startTime));
//
//			}
//		}).start();
//		new Thread(new Runnable() {
//
//			@Override
//			public void run() {
//				List<Integer> array = new ArrayList<>();
//				for (int i = 0; i < 80000; i++) {
//					Random random = new Random();
//					array.add(random.nextInt(80000));
//				}
//				int length = array.size();
//				Integer a[] = array.toArray(new Integer[length]);
//				long startTime = System.currentTimeMillis();
//				SortMethod.<Integer>insertSort(a, 0, a.length - 1);
//				System.out.println("插入法结果：" + Arrays.toString(a));
//				System.out.println("插入法耗时：" + (System.currentTimeMillis() - startTime));
//
//			}
//		}).start();
//		new Thread(new Runnable() {
//
//			@Override
//			public void run() {
//				List<Integer> array = new ArrayList<>();
//				for (int i = 0; i < 80000; i++) {
//					Random random = new Random();
//					array.add(random.nextInt(80000));
//				}
//				int length = array.size();
//				Integer a[] = array.toArray(new Integer[length]);
//				long startTime = System.currentTimeMillis();
//				SortMethod.cocktailSort(a, a.length);
//				System.out.println("鸡尾酒耗时：" + (System.currentTimeMillis() - startTime));
////				System.out.println("排序后：" + Arrays.toString(a1));
//
//			}
//		}).start();
//		int a[] = {33,56,1,25,98,22,34};
//		SortMethod.basket(a);
//		System.out.println("排序后：" + Arrays.toString(a));
//		List<String> arrayList=new ArrayList<>();
//		arrayList.add("ssss");
//		arrayList.add("rrr");
//		arrayList.add("gggg");
//		arrayList.add("ggsd");
//		arrayList.add("erewer");
//		arrayList.add("werqe");
//		arrayList.add("ertyrty");
//		arrayList.add("jgfjdfgjh");
//		HashMap<String, String> hashMap=new HashMap<>();
//		hashMap.put("userNmae", "张三");
//		hashMap.put("sex", "男");
//		hashMap.put("a", "张三");
//		hashMap.put("b", "张三");
//		hashMap.put("c", "张三");
//		hashMap.put("d", "张三");
//		Gson gson=new Gson();
//		String json=gson.toJson(arrayList);
//		String json2=gson.toJson(hashMap);
//		System.out.println(json);
//		System.out.println(json2);
        //  求 2/1 + 3/2 + 5/3 + 8/5 + 13/8 .....前20项之和。
//        double x = 2.0;
//        double y = 1.0;
//        double z = 0.0;
//        double sum = 0.0;
//        for (int i = 1; i <= 20; i++) {
//            sum += sum + x / y;
//            z = x;
//            x = x + y;
//            y = z;
//        }
//        System.out.println("前20项和为：" + sum);

//        File file = new File("D:/Workspace/Android/Dresslily/Dresslily/app/build/outputs/apk/release/Dresslily_v2.3.4_api_v2.2_release_1806281429.apk");
//        System.out.println(getBuildType(file));
        try {
//            long startTime = System.currentTimeMillis();
//            String url = "http://10.36.5.100:8080/jenkins/job/Rosegal-预发布/879/artifact/app/build/outputs/apk/rosegal/preRelease/rosegal_v3.6.2_preRelease_1909041845.apk";
//            String hostAddress = "10.36.5.68";
//            URL hostUrl = new URL(url);
//            String downloadHost = hostUrl.getHost();
//            System.out.println(downloadHost);
//            if (!hostAddress.equals(downloadHost)) {
//                url = url.replace(downloadHost, hostAddress);
//            }
//            System.out.println(url);
//            System.out.println("耗时：" + (System.currentTimeMillis() - startTime));
            //0 1 2
            //3 4 5
            //6 7 8
            //9 10 11
//            String url = "https://m.zaful.com/contact-us?is_app=1&app=1&lang=en";
//            URI uri=URI.create(url);
//            String path = uri.getRawPath();
//            String query=uri.getRawQuery();
//            System.out.println(path+"?"+query);
//            System.out.println(lastSpan(11, 3));
//            String ss="We have received the information about your issue and request. Your case will be handled by our dedicated after-sale team.\\r\\nWe will email you within 24 hours with solutions.\\r\\n We appreciate your understanding and patience";
//            String content = "We have received the information about your issue and request.\\n Your case will be handled by our dedicated after-sale team.\\r\\nWe will email you within 24 hours with solutions.\r\n We appreciate your understanding and patience";
//            content = content.replace("\\r\\n", "<br/>");
//            content = content.replace("\\n", "<br/>");
//            System.out.println(content);
//            ss = ss.replace("\\r\\n", "<br/>");
//            ss = ss.replace("\\n", "<br/>");
//            System.out.println(ss);
            System.out.println(tableSizeFor(500));
            parseApk(new File("E:\\Workspace\\Java\\IM演示项目_20191104_171817.apk"));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void parseApk(File appPath) {
        ApkFile apkParser = null;
        try {
            String path = appPath.getPath();
            apkParser = new ApkFile(new File(path));
            ApkMeta apkMeta = apkParser.getApkMeta();
            List<IconFace> iconFaces = apkParser.getAllIcons();
            String iconPath = null;
            if (iconFaces != null && iconFaces.size() > 0) {
                int density = 0;
                for (IconFace iconFace : iconFaces) {
                    final String iconPathTm = iconFace.getPath();
                    if (iconPathTm.endsWith(".png") || iconPathTm.endsWith(".PNG")
                            || iconPathTm.endsWith(".jpg") || iconPathTm.endsWith(".jpeg")) {
                        System.out.println("PluginIcon--- >iconPath:" + iconPath);
                        if (iconFace instanceof Icon) {
                            Icon icon = (Icon) iconFace;
                            if (density < icon.getDensity()) {
                                density = icon.getDensity();
                                iconPath = iconFace.getPath();
                            }
                        } else if (iconFace instanceof AdaptiveIcon) {
                            AdaptiveIcon adaptiveIcon = (AdaptiveIcon) iconFace;
                            Icon foregroundIcon = adaptiveIcon.getForeground();
                            if (density < foregroundIcon.getDensity()) {
                                density = foregroundIcon.getDensity();
                                iconPath = iconFace.getPath();
                            }
                        }
                    }
                }
            }
            if (iconPath != null) {
                System.out.println("PluginIcon--- >" + iconPath);
            }
            System.out.println("PluginVersionCode--- >" + apkMeta.getVersionCode());
            System.out.println("PluginVersionName--- >" + apkMeta.getVersionName());
            System.out.println("AppName--- >" + apkMeta.getName());
            System.out.println("PackageName--- >" + apkMeta.getPackageName());
            System.out.println("PluginVersionName--- >" + apkMeta.getVersionName());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                apkParser.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    static final int MAXIMUM_CAPACITY = 1 << 30;

    /**
     * Returns a power of two size for the given target capacity.
     */
    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }

    private static int lastSpan(int position, int spanCount) {
        return position % spanCount;
    }

    private static String getBuildType(File apkFile) {
        File file = apkFile.getParentFile();
        String fileName = file.getName();
        return fileName;
    }

    public static String getIP(String url) {
        //使用正则表达式过滤，
        String re = "((http|ftp|https)://)(([a-zA-Z0-9._-]+)|([0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}.[0-9]{1,3}))(([a-zA-Z]{2,6})|(:[0-9]{1,4})?)";
        String str = "";
        // 编译正则表达式
        Pattern pattern = Pattern.compile(re);
        // 忽略大小写的写法
        // Pattern pat = Pattern.compile(regEx, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(url);
        //若url==http://127.0.0.1:9040或www.baidu.com的，正则表达式表示匹配
        if (matcher.matches()) {
            str = url;
        } else {
            String[] split2 = url.split(re);
            if (split2.length > 1) {
                String substring = url.substring(0, url.length() - split2[1].length());
                str = substring;
            } else {
                str = split2[0];
            }
        }
        return str;
    }
}
//public class Test {
//	static int arr[]=new int[5];
//	public static void main(String[] args) {
//		// System.out.println(toBinaryFormString("1110111010"));
//		// System.out.println(toBinaryString(1110111010L));
//		// System.out.println(strToBinstr("1110111010"));
//		// 1110111010
//		
//		System.out.println(factorial(8));
//	}
//	
//	static long factorial(int n){
//		if(n==1){
//			return 1;
//		}else {
//			return n*factorial(n-1);
//		}
//	}
//
//	private static String strToBinstr(String str) {
//		char[] strChar = str.toCharArray();
//		String result = "";
//		for (int i = 0; i < strChar.length; i++) {
//			result += Integer.toBinaryString(strChar[i]) + " ";
//		}
//		return result;
//	}
//
//	private static String toBinaryString(Long value) {
//		ArrayList<Long> result = new ArrayList<>();
//		do {
//			result.add(value % 2);
//			value = value / 2;
//		} while (value != 0);
//		Collections.reverse(result);
//		return Arrays.toString(result.toArray(new Long[result.size()]));
//	}
//
//	private static String toBinaryFormString(String strInput) {
//		int nInputLength = strInput.length();
//		String strBinary = "";
//		for (int i = 0; i < nInputLength; i++) {
//			strBinary += outc(strInput.charAt(i));
//		}
//		return strBinary;
//
//	}
//
//	static String outc(char c) {
//		char k = 0x80;
//		String result = "";
//		for (int i = 0; i < 8; i++, k >>= 1) {
//			if ((c & k) != 0) {
//				result += "1";
//			} else {
//				result += "0";
//			}
//		}
//		return result;
//	}
//}
