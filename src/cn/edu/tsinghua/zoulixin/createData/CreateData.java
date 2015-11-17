package cn.edu.tsinghua.zoulixin.createData;

import java.awt.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class CreateData {
	public static void main(String[] args) {
		List Province = new List();
		List Commodity = new List();
		// 读取省份的数据
		try {
			String encoding = "utf-8";
			File file = new File(
					"C:\\Users\\Administrator\\Desktop\\能说会道\\TalkAssistance\\src\\cn\\edu\\tsinghua\\zoulixin\\createData\\sheng.txt");
			if (file.isFile() && file.exists()) { // 判断文件是否存在
				InputStreamReader read = new InputStreamReader(
						new FileInputStream(file), encoding);// 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					Province.add(lineTxt);
				}
				read.close();
			} else {
				System.out.println("找不到指定的文件");
			}
		} catch (Exception e) {
			System.out.println("读取文件内容出错");
			e.printStackTrace();
		}
		// 读取产品数据
		try {
			String encoding = "utf-8";
			File file = new File(
					"C:\\Users\\Administrator\\Desktop\\能说会道\\TalkAssistance\\src\\cn\\edu\\tsinghua\\zoulixin\\createData\\chanpin.txt");
			if (file.isFile() && file.exists()) { // 判断文件是否存在
				InputStreamReader read = new InputStreamReader(
						new FileInputStream(file), encoding);// 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					Commodity.add(lineTxt);
				}
				read.close();
			} else {
				System.out.println("找不到指定的文件");
			}
		} catch (Exception e) {
			System.out.println("读取文件内容出错");
			e.printStackTrace();
		}
		// 读取销售记录
		Map<String, Integer> selling = new HashMap<String, Integer>();
		Map<String, Integer> selling1 = new HashMap<String, Integer>();
		Map<String, Integer> selling2 = new HashMap<String, Integer>();
		try {
			String encoding = "utf-8";
			File file = new File(
					"C:\\Users\\Administrator\\Desktop\\能说会道\\TalkAssistance\\src\\cn\\edu\\tsinghua\\zoulixin\\createData\\shengData.txt");
			if (file.isFile() && file.exists()) { // 判断文件是否存在
				InputStreamReader read = new InputStreamReader(
						new FileInputStream(file), encoding);// 考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				String temp[] = null;
				while ((lineTxt = bufferedReader.readLine()) != null) {
					temp = lineTxt.split(" +");
					selling.put(temp[1] + temp[0] + temp[2] + temp[4],
							Integer.valueOf(temp[5]));
				}
				read.close();
			} else {
				System.out.println("找不到指定的文件");
			}
		} catch (Exception e) {
			System.out.println("读取文件内容出错");
			e.printStackTrace();
		}

		for (Integer provincenum = 0; provincenum < Province.getItemCount(); provincenum++)
			for (Integer year = 2014; year <= 2015; year++)
				for (Integer season = 1; season <= 4; season++)
					for (Integer commoditynum = 0; commoditynum < Commodity
							.getItemCount(); commoditynum++) {
						selling1.put(
								Province.getItem(provincenum) + year + season
										+ Commodity.getItem(commoditynum),
								selling.get(Province.getItem(provincenum)
										+ year.toString()
										+ ((season - 1) * 3 + 1)
										+ Commodity.getItem(commoditynum))
										+ selling.get(Province
												.getItem(provincenum)
												+ year.toString()
												+ ((season - 1) * 3 + 2)
												+ Commodity
														.getItem(commoditynum))
										+ selling.get(Province
												.getItem(provincenum)
												+ year.toString()
												+ ((season - 1) * 3 + 3)
												+ Commodity
														.getItem(commoditynum)));
					}

		for (Integer provincenum = 0; provincenum < Province.getItemCount(); provincenum++)
			for (Integer year = 2014; year <= 2015; year++)
				for (Integer commoditynum = 0; commoditynum < Commodity
						.getItemCount(); commoditynum++) {
					selling2.put(
							Province.getItem(provincenum) + year
									+ Commodity.getItem(commoditynum),
							selling.get(Province.getItem(provincenum)
									+ year.toString() + 1
									+ Commodity.getItem(commoditynum))
									+ selling.get(Province.getItem(provincenum)
											+ year.toString() + 2
											+ Commodity.getItem(commoditynum))
									+ selling.get(Province.getItem(provincenum)
											+ year.toString() + 3
											+ Commodity.getItem(commoditynum))
									+ selling.get(Province.getItem(provincenum)
											+ year.toString() + 4
											+ Commodity.getItem(commoditynum))
									+ selling.get(Province.getItem(provincenum)
											+ year.toString() + 5
											+ Commodity.getItem(commoditynum))
									+ selling.get(Province.getItem(provincenum)
											+ year.toString() + 6
											+ Commodity.getItem(commoditynum))
									+ selling.get(Province.getItem(provincenum)
											+ year.toString() + 7
											+ Commodity.getItem(commoditynum))
									+ selling.get(Province.getItem(provincenum)
											+ year.toString() + 8
											+ Commodity.getItem(commoditynum))
									+ selling.get(Province.getItem(provincenum)
											+ year.toString() + 9
											+ Commodity.getItem(commoditynum))
									+ selling.get(Province.getItem(provincenum)
											+ year.toString() + 10
											+ Commodity.getItem(commoditynum))
									+ selling.get(Province.getItem(provincenum)
											+ year.toString() + 11
											+ Commodity.getItem(commoditynum))
									+ selling.get(Province.getItem(provincenum)
											+ year.toString() + 12
											+ Commodity.getItem(commoditynum)));
				}

		// 创建写出的数据
		try {
			File file = new File(
					"C:\\Users\\Administrator\\Desktop\\能说会道\\TalkAssistance\\src\\cn\\edu\\tsinghua\\zoulixin\\createData\\datasource.txt");
			if (!file.exists()) {
				file.createNewFile();
			}
			FileOutputStream txtfile = new FileOutputStream(file);
			PrintStream p = new PrintStream(txtfile);
			// 模板1 XX城市在某月XX商品的销售额
			for (Integer provincenum = 0; provincenum < Province.getItemCount(); provincenum++)
				for (Integer year = 2014; year <= 2015; year++)
					for (Integer month = 1; month <= 12; month++)
						for (Integer commoditynum = 0; commoditynum < Commodity
								.getItemCount(); commoditynum++) {
							Integer currentMonth = selling.get(Province
									.getItem(provincenum)
									+ year.toString()
									+ month.toString()
									+ Commodity.getItem(commoditynum));
							if (month == 1 && year == 2014) {
								p.println(year.toString() + "年"
										+ month.toString() + "月"
										+ Province.getItem(provincenum) + "销售"
										+ Commodity.getItem(commoditynum)
										+ "的销售额是" + currentMonth + "万元");
							} else {
								Integer lastMonth = 0;
								if (month != 1) {
									lastMonth = selling.get(Province
											.getItem(provincenum)
											+ year.toString()
											+ (month - 1)
											+ Commodity.getItem(commoditynum));
									double percent = ((currentMonth - lastMonth) * 100.0 / lastMonth);
									BigDecimal b = new BigDecimal(percent);
									percent = b.setScale(2,
											BigDecimal.ROUND_HALF_UP)
											.doubleValue();
									if (percent > 0)
										p.println(year.toString()
												+ "年"
												+ month.toString()
												+ "月"
												+ Province.getItem(provincenum)
												+ "销售"
												+ Commodity
														.getItem(commoditynum)
												+ "的销售额是" + currentMonth + "万元"
												+ ",相比上个月增长了百分之" + percent);
									else if (percent < 0) {
										p.println(year.toString()
												+ "年"
												+ month.toString()
												+ "月"
												+ Province.getItem(provincenum)
												+ "销售"
												+ Commodity
														.getItem(commoditynum)
												+ "的销售额是" + currentMonth + "万元"
												+ ",相比上个月减少了百分之" + percent
												* (-1));
									} else if (percent == 0) {
										p.println(year.toString()
												+ "年"
												+ month.toString()
												+ "月"
												+ Province.getItem(provincenum)
												+ "销售"
												+ Commodity
														.getItem(commoditynum)
												+ "的销售额是" + currentMonth + "万元"
												+ ",与上个月持平");
									}
								} else {
									lastMonth = selling.get(Province
											.getItem(provincenum)
											+ (year - 1)
											+ (12)
											+ Commodity.getItem(commoditynum));
									double percent = ((currentMonth - lastMonth) * 100.0 / lastMonth);
									BigDecimal b = new BigDecimal(percent);
									percent = b.setScale(2,
											BigDecimal.ROUND_HALF_UP)
											.doubleValue();
									if (percent > 0)
										p.println(year.toString()
												+ "年"
												+ month.toString()
												+ "月"
												+ Province.getItem(provincenum)
												+ "销售"
												+ Commodity
														.getItem(commoditynum)
												+ "的销售额是" + currentMonth + "万元"
												+ ",相比上个月增长了百分之" + percent);
									else if (percent < 0) {
										p.println(year.toString()
												+ "年"
												+ month.toString()
												+ "月"
												+ Province.getItem(provincenum)
												+ "销售"
												+ Commodity
														.getItem(commoditynum)
												+ "的销售额是" + currentMonth + "万元"
												+ ",相比上个月减少了百分之" + percent
												* (-1));
									} else if (percent == 0) {
										p.println(year.toString()
												+ "年"
												+ month.toString()
												+ "月"
												+ Province.getItem(provincenum)
												+ "销售"
												+ Commodity
														.getItem(commoditynum)
												+ "的销售额是" + currentMonth + "万元"
												+ ",与上个月持平");
									}
								}
							}
						}
			// 模板2 XX城市在XX季度XX商品的销售额是
			for (Integer provincenum = 0; provincenum < Province.getItemCount(); provincenum++)
				for (Integer year = 2014; year <= 2015; year++)
					for (Integer season = 1; season <= 4; season++)
						for (Integer commoditynum = 0; commoditynum < Commodity
								.getItemCount(); commoditynum++) {
							Integer currentSeason = selling1.get(Province
									.getItem(provincenum)
									+ year.toString()
									+ season.toString()
									+ Commodity.getItem(commoditynum));
							if (season == 1 && year == 2014) {
								p.println(year.toString() + "年第"
										+ season.toString() + "季度"
										+ Province.getItem(provincenum) + "销售"
										+ Commodity.getItem(commoditynum)
										+ "的销售额是" + currentSeason + "万元");
							} else {
								Integer lastMonth = 0;
								if (season != 1) {
									lastMonth = selling1.get(Province
											.getItem(provincenum)
											+ year.toString()
											+ (season - 1)
											+ Commodity.getItem(commoditynum));
									double percent = ((currentSeason - lastMonth) * 100.0 / lastMonth);
									BigDecimal b = new BigDecimal(percent);
									percent = b.setScale(2,
											BigDecimal.ROUND_HALF_UP)
											.doubleValue();
									if (percent > 0)
										p.println(year.toString()
												+ "年第"
												+ season.toString()
												+ "季度"
												+ Province.getItem(provincenum)
												+ "销售"
												+ Commodity
														.getItem(commoditynum)
												+ "的销售额是" + currentSeason + "万元"
												+ ",相比上个季度增长了百分之" + percent);
									else if (percent < 0) {
										p.println(year.toString()
												+ "年第"
												+ season.toString()
												+ "季度"
												+ Province.getItem(provincenum)
												+ "销售"
												+ Commodity
														.getItem(commoditynum)
												+ "的销售额是" + currentSeason + "万元"
												+ ",相比上个季度减少了百分之" + percent
												* (-1));
									} else if (percent == 0) {
										p.println(year.toString()
												+ "年第"
												+ season.toString()
												+ "季度"
												+ Province.getItem(provincenum)
												+ "销售"
												+ Commodity
														.getItem(commoditynum)
												+ "的销售额是" + currentSeason + "万元"
												+ ",与上个季度持平");
									}
								} else {
									lastMonth = selling.get(Province
											.getItem(provincenum)
											+ (year - 1)
											+ (4)
											+ Commodity.getItem(commoditynum));
									double percent = ((currentSeason - lastMonth) * 100.0 / lastMonth);
									BigDecimal b = new BigDecimal(percent);
									percent = b.setScale(2,
											BigDecimal.ROUND_HALF_UP)
											.doubleValue();
									if (percent > 0)
										p.println(year.toString()
												+ "年第"
												+ season.toString()
												+ "季度"
												+ Province.getItem(provincenum)
												+ "销售"
												+ Commodity
														.getItem(commoditynum)
												+ "的销售额是" + currentSeason + "万元"
												+ ",相比上个季度增长了百分之" + percent);
									else if (percent < 0) {
										p.println(year.toString()
												+ "年第"
												+ season.toString()
												+ "季度"
												+ Province.getItem(provincenum)
												+ "销售"
												+ Commodity
														.getItem(commoditynum)
												+ "的销售额是" + currentSeason + "万元"
												+ ",相比上个季度减少了百分之" + percent
												* (-1));
									} else if (percent == 0) {
										p.println(year.toString()
												+ "年第"
												+ season.toString()
												+ "季度"
												+ Province.getItem(provincenum)
												+ "销售"
												+ Commodity
														.getItem(commoditynum)
												+ "的销售额是" + currentSeason + "万元"
												+ ",与上个季度持平");
								}
							}
						}
							}
			
			// 模板3 XX城市在XX年XX商品的销售额是
			for (Integer provincenum = 0; provincenum < Province.getItemCount(); provincenum++)
				for (Integer year = 2014; year <= 2015; year++)
					for (Integer commoditynum = 0; commoditynum < Commodity
							.getItemCount(); commoditynum++) {
						if (year == 2014) {
							p.println(year.toString()
									+ "年"
									+ Province.getItem(provincenum)
									+ "销售"
									+ Commodity.getItem(commoditynum)
									+ "的销售额是"
									+ selling2.get(Province
											.getItem(provincenum)
											+ year
											+ Commodity.getItem(commoditynum))
									+ "万元");
						}
						else if(year==2015)
						{
							Integer lastYear = selling2.get(Province
									.getItem(provincenum)
									+ (year - 1)
									+ Commodity.getItem(commoditynum));
							Integer currentYear = selling2.get(Province
									.getItem(provincenum)
									+ year
									+ Commodity.getItem(commoditynum));
							double percent = ((currentYear- lastYear) * 100.0 / lastYear);
							BigDecimal b = new BigDecimal(percent);
							percent = b.setScale(2,
									BigDecimal.ROUND_HALF_UP)
									.doubleValue();
							if (percent > 0)
								p.println(year.toString()
										+ "年"
										+ Province.getItem(provincenum)
										+ "销售"
										+ Commodity
												.getItem(commoditynum)
										+ "的销售额是" + currentYear + "万元"
										+ ",相比去年增长了百分之" + percent);
							else if (percent < 0) {
								p.println(year.toString()
										+ "年"
										+ Province.getItem(provincenum)
										+ "销售"
										+ Commodity
												.getItem(commoditynum)
										+ "的销售额是" + currentYear + "万元"
										+ ",相比去年减少了百分之" + percent
										* (-1));
							} else if (percent == 0) {
								p.println(year.toString()
										+ "年"
										+ Province.getItem(provincenum)
										+ "销售"
										+ Commodity
												.getItem(commoditynum)
										+ "的销售额是" + currentYear + "万元"
										+ ",与去年持平");
							}
						}
					}
			
			
			
			
			// 模板4 XX城市在XX年的销售额是
			Map<String, Integer> selling3=new HashMap<>();
			for (Integer provincenum = 0; provincenum < Province.getItemCount(); provincenum++) {
				for (Integer year = 2014; year <= 2015; year++) {
					Integer temp=0;
						for (Integer commoditynum = 0; commoditynum < Commodity
								.getItemCount(); commoditynum++) {
							temp = temp
									+ selling2.get(Province.getItem(provincenum)
											+ year.toString()
											+ Commodity.getItem(commoditynum));
						}
					selling3.put(Province.getItem(provincenum)+year, temp);
				}
			}
			for (Integer provincenum = 0; provincenum < Province.getItemCount(); provincenum++) {
				for (Integer year = 2014; year <= 2015; year++) {
					if(year==2014)
					{
						p.println(year+"年"+Province.getItem(provincenum)+"的销售总额是"+selling3.get(Province.getItem(provincenum)+year)+"万元");
					}
					else {
						Integer lastYear = selling3.get(Province
								.getItem(provincenum)
								+ (year - 1));
						Integer currentYear = selling3.get(Province
								.getItem(provincenum)
								+ year);
						double percent = ((currentYear- lastYear) * 100.0 / lastYear);
						BigDecimal b = new BigDecimal(percent);
						percent = b.setScale(2,
								BigDecimal.ROUND_HALF_UP)
								.doubleValue();
						if (percent > 0)
							p.println(year.toString()
									+ "年"
									+ Province.getItem(provincenum)
									+ "销售"
									+ "的销售总额是" + currentYear + "万元"
									+ ",相比去年增长了百分之" + percent);
						else if (percent < 0) {
							p.println(year.toString()
									+ "年"
									+ Province.getItem(provincenum)
									+ "销售"
									+ "的销售总额是" + currentYear + "万元"
									+ ",相比去年减少了百分之" + percent
									* (-1));
						} else if (percent == 0) {
							p.println(year.toString()
									+ "年"
									+ Province.getItem(provincenum)
									+ "销售"
									+ "的销售额是" + currentYear + "万元"
									+ ",与去年持平");
						}
					}
				}
			}
			
			
			
			
			
			
			
			
			
			// 模板4 XX产品xx年月的销售额是多少
			Map<String, Integer> selling4=new HashMap<>();
			for (Integer commoditynum = 0; commoditynum < Commodity
					.getItemCount(); commoditynum++)
				for (Integer year = 2014; year <= 2015; year++)
					for (Integer month = 1; month <= 12; month++) {
						Integer temp = 0;
						for (Integer provincenum = 0; provincenum < Province
								.getItemCount(); provincenum++) {
							temp = temp
									+ selling.get(Province.getItem(provincenum)
											+ year.toString()
											+ month.toString()
											+ Commodity.getItem(commoditynum));
						}
						selling4.put(Commodity.getItem(commoditynum)+year+month,temp);
					}
			for (Integer commoditynum = 0; commoditynum < Commodity
					.getItemCount(); commoditynum++)
				for (Integer year = 2014; year <= 2015; year++)
					for (Integer month = 1; month <= 12; month++) {
						Integer currentMonth = selling4.get(Commodity.
								getItem(commoditynum)
								+ year.toString()
								+ month.toString());
						if (month == 1 && year == 2014) {
							p.println(year.toString() + "年"
									+ month.toString() + "月"
									+"销售"
									+ Commodity.getItem(commoditynum)
									+ "的销售额是" + currentMonth + "万元");
						} else {
							Integer lastMonth = 0;
							if (month != 1) {
								lastMonth = selling4.get(Commodity.getItem(commoditynum)
										+ year.toString()
										+ (month - 1));
								double percent = ((currentMonth - lastMonth) * 100.0 / lastMonth);
								BigDecimal b = new BigDecimal(percent);
								percent = b.setScale(2,
										BigDecimal.ROUND_HALF_UP)
										.doubleValue();
								if (percent > 0)
									p.println(year.toString()
											+ "年"
											+ month.toString()
											+ "月"
											+ "销售"
											+ Commodity.getItem(commoditynum)
											+ "的销售额是" + currentMonth + "万元"
											+ ",相比上个月增长了百分之" + percent);
								else if (percent < 0) {
									p.println(year.toString()
											+ "年"
											+ month.toString()
											+ "月"
											+ "销售"
											+ Commodity
													.getItem(commoditynum)
											+ "的销售额是" + currentMonth + "万元"
											+ ",相比上个月减少了百分之" + percent
											* (-1));
								} else if (percent == 0) {
									p.println(year.toString()
											+ "年"
											+ month.toString()
											+ "月"
											+ "销售"
											+ Commodity
													.getItem(commoditynum)
											+ "的销售额是" + currentMonth + "万元"
											+ ",与上个月持平");
								}
							} else {
								lastMonth = selling4.get(Commodity.getItem(commoditynum)
										+ (year-1)
										+ 12);
								double percent = ((currentMonth - lastMonth) * 100.0 / lastMonth);
								BigDecimal b = new BigDecimal(percent);
								percent = b.setScale(2,
										BigDecimal.ROUND_HALF_UP)
										.doubleValue();
								if (percent > 0)
									p.println(year.toString()
											+ "年"
											+ month.toString()
											+ "月"
											+ "销售"
											+ Commodity
													.getItem(commoditynum)
											+ "的销售额是" + currentMonth + "万元"
											+ ",相比上个月增长了百分之" + percent);
								else if (percent < 0) {
									p.println(year.toString()
											+ "年"
											+ month.toString()
											+ "月"
											+ "销售"
											+ Commodity
													.getItem(commoditynum)
											+ "的销售额是" + currentMonth + "万元"
											+ ",相比上个月减少了百分之" + percent
											* (-1));
								} else if (percent == 0) {
									p.println(year.toString()
											+ "年"
											+ month.toString()
											+ "月"
											+ "销售"
											+ Commodity
													.getItem(commoditynum)
											+ "的销售额是" + currentMonth + "万元"
											+ ",与上个月持平");
								}
							}
						}
					}

			// 模板5 XX产品xx季度的销售额是多少
			Map<String, Integer> selling5=new HashMap<String,Integer>();
			for (Integer commoditynum = 0; commoditynum < Commodity
					.getItemCount(); commoditynum++)
				for (Integer year = 2014; year <= 2015; year++)
					for (Integer month = 1; month <= 4; month++) {
						Integer temp = 0;
						for (Integer provincenum = 0; provincenum < Province
								.getItemCount(); provincenum++) {
							temp = temp
									+ selling.get(Province.getItem(provincenum)
											+ year.toString()
											+ ((month - 1) * 3 + 1)
											+ Commodity.getItem(commoditynum))
									+ selling.get(Province.getItem(provincenum)
											+ year.toString()
											+ ((month - 1) * 3 + 2)
											+ Commodity.getItem(commoditynum))
									+ selling.get(Province.getItem(provincenum)
											+ year.toString()
											+ ((month - 1) * 3 + 3)
											+ Commodity.getItem(commoditynum));
						}
						selling5.put(Commodity.getItem(commoditynum)+year.toString() + month.toString(),temp);
					}
			for (Integer commoditynum = 0; commoditynum < Commodity
					.getItemCount(); commoditynum++)
				for (Integer year = 2014; year <= 2015; year++)
					for (Integer month = 1; month <= 4; month++) {
						Integer currentMonth = selling5.get(Commodity.
								getItem(commoditynum)
								+ year.toString()
								+ month.toString());
						if (month == 1 && year == 2014) {
							p.println(year.toString() + "年在"
									+ month.toString() + "季度"
									+"销售"
									+ Commodity.getItem(commoditynum)
									+ "的销售额是" + currentMonth + "万元");
						} else {
							Integer lastMonth = 0;
							if (month != 1) {
								lastMonth = selling5.get(Commodity.getItem(commoditynum)
										+ year.toString()
										+ (month - 1));
								double percent = ((currentMonth - lastMonth) * 100.0 / lastMonth);
								BigDecimal b = new BigDecimal(percent);
								percent = b.setScale(2,
										BigDecimal.ROUND_HALF_UP)
										.doubleValue();
								if (percent > 0)
									p.println(year.toString()
											+ "年在"
											+ month.toString()
											+ "季度"
											+ "销售"
											+ Commodity.getItem(commoditynum)
											+ "的销售额是" + currentMonth + "万元"
											+ ",相比上个季度增长了百分之" + percent);
								else if (percent < 0) {
									p.println(year.toString()
											+ "年"
											+ month.toString()
											+ "季度"
											+ "销售"
											+ Commodity
													.getItem(commoditynum)
											+ "的销售额是" + currentMonth + "万元"
											+ ",相比上个季度减少了百分之" + percent
											* (-1));
								} else if (percent == 0) {
									p.println(year.toString()
											+ "年"
											+ month.toString()
											+ "季度"
											+ "销售"
											+ Commodity
													.getItem(commoditynum)
											+ "的销售额是" + currentMonth + "万元"
											+ ",与上个季度持平");
								}
							} else {
								lastMonth = selling5.get(Commodity.getItem(commoditynum)
										+ (year-1)
										+ 4);
								double percent = ((currentMonth - lastMonth) * 100.0 / lastMonth);
								BigDecimal b = new BigDecimal(percent);
								percent = b.setScale(2,
										BigDecimal.ROUND_HALF_UP)
										.doubleValue();
								if (percent > 0)
									p.println(year.toString()
											+ "年"
											+ month.toString()
											+ "季度"
											+ "销售"
											+ Commodity
													.getItem(commoditynum)
											+ "的销售额是" + currentMonth + "万元"
											+ ",相比上个季度增长了百分之" + percent);
								else if (percent < 0) {
									p.println(year.toString()
											+ "年"
											+ month.toString()
											+ "季度"
											+ "销售"
											+ Commodity
													.getItem(commoditynum)
											+ "的销售额是" + currentMonth + "万元"
											+ ",相比上个季度减少了百分之" + percent
											* (-1));
								} else if (percent == 0) {
									p.println(year.toString()
											+ "年"
											+ month.toString()
											+ "季度"
											+ "销售"
											+ Commodity
													.getItem(commoditynum)
											+ "的销售额是" + currentMonth + "万元"
											+ ",与上个季度持平");
								}
							}
						}
					}
			
			
		
			// 模板6 XX产品XX年的销售额
			Map<String, Integer> selling6=new HashMap<String,Integer>();
			for (Integer commoditynum = 0; commoditynum < Commodity
					.getItemCount(); commoditynum++)
				for (Integer year = 2014; year <= 2015; year++) {
					Integer temp = 0;
					for (Integer month = 1; month <= 12; month++)
						for (Integer provincenum = 0; provincenum < Province
								.getItemCount(); provincenum++)
							temp = temp
									+ selling.get(Province.getItem(provincenum)
											+ year.toString()
											+ month.toString()
											+ Commodity.getItem(commoditynum));
					selling6.put(Commodity.getItem(commoditynum)+year.toString(),temp);
				}
			for (Integer commoditynum = 0; commoditynum < Commodity
					.getItemCount(); commoditynum++)
				for (Integer year = 2014; year <= 2015; year++) {
					if(year==2014)
						p.println(year+"年"+Commodity.getItem(commoditynum)+"的销售总额是"+selling6.get(Commodity.getItem(commoditynum)+year)+"万元");
					else{
						Integer lastYear = selling6.get(Commodity.getItem(commoditynum)
								+ (year - 1));
						Integer currentYear = selling6.get(Commodity.getItem(commoditynum)
								+ year);
						double percent = ((currentYear- lastYear) * 100.0 / lastYear);
						BigDecimal b = new BigDecimal(percent);
						percent = b.setScale(2,
								BigDecimal.ROUND_HALF_UP)
								.doubleValue();
						if (percent > 0)
							p.println(year.toString()
									+ "年"
									+ "销售"
									+Commodity.getItem(commoditynum)
									+ "的销售总额是" + currentYear + "万元"
									+ ",相比去年增长了百分之" + percent);
						else if (percent < 0) {
							p.println(year.toString()
									+ "年"
									+ "销售"
									+Commodity.getItem(commoditynum)
									+ "的销售总额是" + currentYear + "万元"
									+ ",相比去年增长了百分之" + percent
									* (-1));
						} else if (percent == 0) {
							p.println(year.toString()
									+ "年"
									+ "销售"
									+Commodity.getItem(commoditynum)
									+ "的销售总额是" + currentYear + "万元"
									+ ",与去年持平");
						}
					}
					}
				
			
			
			
			// 模板7 xx产品在XX年在哪里销售的最好、最差
			for (Integer commoditynum = 0; commoditynum < Commodity
					.getItemCount(); commoditynum++)
				for (Integer year = 2014; year <= 2015; year++) {
					Integer proMax = 0;
					Integer proMin = 0;
					Integer maxInteger = 0;
					Integer minInteger = 10000000;
					for (Integer provincenum = 0; provincenum < Province
							.getItemCount(); provincenum++) {
						Integer temp = 0;
						for (Integer month = 1; month <= 12; month++)
							temp = temp
									+ selling.get(Province.getItem(provincenum)
											+ year.toString()
											+ month.toString()
											+ Commodity.getItem(commoditynum));
						if (temp > maxInteger) {
							maxInteger = temp;
							proMax = provincenum;
						}
						if (temp < minInteger) {
							minInteger = temp;
							proMin = provincenum;
						}
					}
					p.println(year.toString() + "年"
							+ Commodity.getItem(commoditynum) + "在"
							+ Province.getItem(proMax) + "销售额最高,销售额是"
							+ maxInteger + "万元");
					p.println(year.toString() + "年"
							+ Commodity.getItem(commoditynum) + "在"
							+ Province.getItem(proMin) + "销售额最差，销售额是"
							+ minInteger + "万元");
				}
			// 模板8 xx产品在XX年某月在哪里销售的最好、最差
			for (Integer commoditynum = 0; commoditynum < Commodity
					.getItemCount(); commoditynum++)
				for (Integer year = 2014; year <= 2015; year++)
					for (Integer month = 1; month <= 12; month++) {
						Integer proMax = 0;
						Integer proMin = 0;
						Integer maxInteger = 0;
						Integer minInteger = 10000000;

						for (Integer provincenum = 0; provincenum < Province
								.getItemCount(); provincenum++) {
							Integer temp = 0;
							temp = temp
									+ selling.get(Province.getItem(provincenum)
											+ year.toString()
											+ month.toString()
											+ Commodity.getItem(commoditynum));
							if (temp > maxInteger) {
								maxInteger = temp;
								proMax = provincenum;
							}
							if (temp < minInteger) {
								minInteger = temp;
								proMin = provincenum;
							}
						}
						p.println(year.toString() + "年" + month.toString()
								+ "月" + Commodity.getItem(commoditynum) + "在"
								+ Province.getItem(proMax) + "销售额最高，销售额是"
								+ maxInteger + "万元");
						p.println(year.toString() + "年" + month.toString()
								+ "月" + Commodity.getItem(commoditynum) + "在"
								+ Province.getItem(proMin) + "销售额最差,销售额是"
								+ minInteger + "万元");
					}
			txtfile.close();
		} catch (Exception e) {
			System.out.println("读取文件内容出错");
			e.printStackTrace();
		}

	}
}
