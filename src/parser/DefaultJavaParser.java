package parser;

import java.io.File;
import java.io.FilenameFilter;
import java.util.List;

import recoder.CrossReferenceServiceConfiguration;
import recoder.ParserException;
import recoder.io.PropertyNames;
import recoder.io.SourceFileRepository;
import recoder.java.CompilationUnit;
import sememe.AbstractSememe;
import config.GlobalConfig;

public class DefaultJavaParser implements JavaParser {

	@Override
	public List<AbstractSememe> parse() {
		CrossReferenceServiceConfiguration crsc = new CrossReferenceServiceConfiguration();
		crsc.getProjectSettings().setProperty(PropertyNames.INPUT_PATH,
				GlobalConfig.LIB_PATH + ":" + GlobalConfig.INPUT_PATH);
//				GlobalConfig.INPUT_PATH);
		//crsc.getProjectSettings().ensureSystemClassesAreInPath();
		
		GlobalConfig.LOGGER.log("Start parsing in:");
		GlobalConfig.LOGGER.log(crsc.getProjectSettings().getProperty(PropertyNames.INPUT_PATH));
		GlobalConfig.LOGGER.log(crsc.getProjectSettings().getSearchPathList());
		
		SourceFileRepository sfr = crsc.getSourceFileRepository();
		try {
			List<CompilationUnit> cul = sfr.getAllCompilationUnitsFromPath(
					new FilenameFilter(){

						@Override
						public boolean accept(File dir, String name) {
							return dir != null;
						}					
					}
					);
			crsc.getChangeHistory().updateModel();
			for (CompilationUnit cu : cul) {
				if (!cu.getName().contains(GlobalConfig.LIB_PATH)){
					CustomVisitor visitor = new CustomVisitor(crsc);
					cu.accept(visitor);
					GlobalConfig.LOGGER.log(visitor.readableSememe());
					GlobalConfig.LEXEME_RESULT_LOGGER.log(visitor.resultLexemes());
				}
			}
			GlobalConfig.LOGGER.close();
			return null;
		} catch (ParserException e) {
			return null;
		}
	}
}
