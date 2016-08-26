package watermark.service.main;

import junit.framework.TestCase;
import org.apache.log4j.Logger;
import org.junit.Test;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by syrovo01 on 26.08.2016.
 */
public class WatermarkServiceTest extends TestCase {
	final static Logger logger = Logger.getRootLogger();

	// test data source : json files with docs and journals
	// 1) create a list of documents from data source
	// {content:”book”, title:”The Dark Code”, author:”Bruce Wayne”, topic:”Science”}
	// {content:”book”, title:”How to make money”, author:”Dr. Evil”, topic:”Business”}
	// {content:”journal”, title:”Journal of human flight routes”, author:”Clark Kent”}


	@Test
	public void testWatermarkService(){
		Document document1 = new Book("title1", "author1", Topic.BUSINESS);
		Document document2 = new Book("title2", "author2", Topic.SCIENCE);
		Document document3 = new Book("title3", "author3", Topic.MEDIA);
		Document journal1 = new Journal("journal1", "author4");
		Document journal2 = new Journal("journal2", "author5");
		Document journal3 = new Journal("journal3", "author6");
		List<Document> docs = new ArrayList<Document>();
		docs.add(document1);
		docs.add(document2);
		docs.add(document3);
		docs.add(journal1);
		docs.add(journal2);
		docs.add(journal3);
		WatermarkService watermarkService = new WatermarkService();
		for (Document document : docs){
			watermarkService.addDocument(document);
		}
		WatermarkService.runWatermarkService();
		// check if doc is watermarked
		for (Document document : docs){
			if (WatermarkService.getWatermarkedDocument(document.getUniqueID()).getWatermark() != null){
				logger.info("document was watermarked" + document.getTitle());
			}
		}


	}
}