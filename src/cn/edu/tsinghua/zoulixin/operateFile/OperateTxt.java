package cn.edu.tsinghua.zoulixin.operateFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;

public class OperateTxt {
	public static void postTxtFile(String serverUrl, String filePath) {
		try {
			SolrServer solrServer = new HttpSolrServer(serverUrl);
			String encoding = "utf-8";
			File file = new File(filePath);
			if (file.isFile() && file.exists()) { // 判断文件是否存在
				InputStreamReader read = new InputStreamReader(
						new FileInputStream(file), encoding);// 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				List<SolrInputDocument> docList=new ArrayList<SolrInputDocument>();
				while ((lineTxt = bufferedReader.readLine()) != null) {
					SolrInputDocument tempDocument = new SolrInputDocument();
					tempDocument.setField("id", UUID.randomUUID().toString());
					tempDocument.setField("text_t", lineTxt);
					docList.add(tempDocument);
				}
				Iterator<SolrInputDocument> iterator=docList.iterator();
				while(iterator.hasNext())
				{
					solrServer.add(iterator.next());
				}
				solrServer.commit(true,true);
				read.close();
				solrServer.optimize();
			} else {
				System.out.println("找不到指定的文件");
			}
		} catch (Exception e) {
			System.out.println("读取文件内容出错");
			e.printStackTrace();
		}

	}
	public static void deleteTxtFile(String serverUrl, String filePath) {
		try {
			SolrServer solrServer = new HttpSolrServer(serverUrl);
			String encoding = "utf-8";
			File file = new File(filePath);
			if (file.isFile() && file.exists()) { // 判断文件是否存在
				InputStreamReader read = new InputStreamReader(
						new FileInputStream(file), encoding);// 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					solrServer.deleteByQuery("text_t:" + lineTxt);
				}
				solrServer.commit();
				read.close();
			} else {
				System.out.println("找不到指定的文件");
			}
		} catch (Exception e) {
			System.out.println("读取文件内容出错");
			e.printStackTrace();
		}
	}

	public static void deleteAllFile(String serverUrl) {
		SolrServer solrServer = new HttpSolrServer(serverUrl);
		try {
			solrServer.deleteByQuery("*:*");
			solrServer.commit();
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	static SolrDocumentList simpleSolrQuery(SolrServer solr, String query,
			int rows) throws SolrServerException {
		SolrQuery solrQuery = new SolrQuery(query);
		solrQuery.setRows(rows);
		QueryResponse resp = solr.query(solrQuery);
		SolrDocumentList hits = resp.getResults();
		return hits;
	}

	static void prettyPrint(PrintStream out, SolrDocument doc) {
		List<String> sortedFieldNames = new ArrayList<String>(
				doc.getFieldNames());
		Collections.sort(sortedFieldNames);
		out.println();
		for (String field : sortedFieldNames) {
			out.println(String.format("\t%s: %s", field,
					doc.getFieldValue(field)));
		}
		out.println();
		out.println();
	}

	public static void main(String args[]) {
		String serverUrl = (args != null && args.length > 0) ? args[0]
				: "http://localhost:8983/solr/server1";
		String filePath = (args != null && args.length > 1) ? args[1]
				: "C:\\Users\\Administrator\\Desktop\\能说会道\\TalkAssistance\\src\\cn\\edu\\tsinghua\\zoulixin\\post\\datasource.txt";
		postTxtFile(serverUrl, filePath);
//		 deleteAllFile(serverUrl);
	}
}
