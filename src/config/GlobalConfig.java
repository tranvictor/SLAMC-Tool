package config;

import logger.SLAMCLogger;

public class GlobalConfig {
	public static String LIB_PATH="/System/Library/Java/JavaVirtualMachines/1.6.0.jdk/Contents/Classes/classes.jar";
//	public static String INPUT_PATH = "/Users/VuTran/teaching_assistant/se&ml/SLAMC/Eclipse_workspace/SLAMCTool_inputs";
	public static String INPUT_PATH = "SLAMCTool_inputs";
	public static SLAMCLogger LOGGER = new SLAMCLogger("log/default.log");
	public static SLAMCLogger LEXEME_LOGGER = new SLAMCLogger("log/lexeme.log");
}
