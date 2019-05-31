package com.fjut.view.component;

import java.awt.Font;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickUnit;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardXYItemLabelGenerator;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.ui.TextAnchor;

import com.fjut.pojo.vo.ChartVo;
import com.fjut.service.MaterialsSellService;
import com.fjut.util.SpringContextUtils;

/**
 * 报表组件
 * @author LGX
 *
 */
@SuppressWarnings("all")
public class ChartComponent {
	
	/**
	 * 创建JFreeChart
	 */
	public static JFreeChart createChart(String materialsId, String year, int month1, int month2) {
		//实例化MaterialsSellService对象
		MaterialsSellService materialsSellService = SpringContextUtils.getBean(MaterialsSellService.class);
		//根据物料id，年份，月份区间获取报表数据
		List<List<ChartVo>> chartData = materialsSellService.getChartData(materialsId, year, month1, month2);
		//获取进仓数据
		List<ChartVo> inList = chartData.get(0);
		//获取出仓数据
		List<ChartVo> outList = chartData.get(1);
		// 进仓统计
        TimeSeries timeSeries1 = new TimeSeries("进仓", Month.class);
        // 添加数据
        if(inList != null) {
        	for (ChartVo chartVo : inList) {
        		timeSeries1.add(new Month(chartVo.getMonth(), chartVo.getYear()), chartVo.getSum());
        	}
        }
        // 出仓统计
        TimeSeries timeSeries2 = new TimeSeries("出仓", Month.class);
        // 添加数据
        if(outList != null) {
        	for (ChartVo chartVo : outList) {
        		timeSeries2.add(new Month(chartVo.getMonth(), chartVo.getYear()), chartVo.getSum());
			}
        }

        // 定义时间序列的集合
        TimeSeriesCollection lineDataset = new TimeSeriesCollection();
        lineDataset.addSeries(timeSeries1);
        lineDataset.addSeries(timeSeries2);
        JFreeChart chart = ChartFactory.createTimeSeriesChart("Time line graph", "Month", "Sum", lineDataset, true, true, true);
        //设置主标题
        chart.setTitle(new TextTitle("进出仓总量统计对比图"));
        //设置子标题
        TextTitle subtitle = new TextTitle(year + "年度", new Font("宋体", Font.BOLD, 12));
        chart.addSubtitle(subtitle);
        chart.setAntiAlias(true);

        //设置时间轴的范围。
        XYPlot plot = (XYPlot) chart.getPlot();
        DateAxis dateaxis = (DateAxis) plot.getDomainAxis();
        dateaxis.setDateFormatOverride(new SimpleDateFormat("M"));
        dateaxis.setTickUnit(new DateTickUnit(DateTickUnit.MONTH, 1));

        //设置曲线是否显示数据点
        XYLineAndShapeRenderer xylinerenderer = (XYLineAndShapeRenderer) plot.getRenderer();
        xylinerenderer.setBaseShapesVisible(true);

        //设置曲线显示各数据点的值
        XYItemRenderer xyitem = plot.getRenderer();
        xyitem.setBaseItemLabelsVisible(true);
        xyitem.setBasePositiveItemLabelPosition(new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_CENTER));
        xyitem.setBaseItemLabelGenerator(new StandardXYItemLabelGenerator());
        xyitem.setBaseItemLabelFont(new Font("Dialog", Font.BOLD, 12));
        plot.setRenderer(xyitem);
        return chart;
	}
	
	/**
	 * 创建报表面板
	 * @return
	 */
	public static JPanel getChartPanel(String materialsId, String year, int month1, int month2) {
		JFreeChart chart = createChart(materialsId, year, month1, month2);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setVisible(true);
        return chartPanel;
	}
	
	/**
	 * 创建报表窗口
	 */
	public static void createChartFrame(String materialsId, String year, int month1, int month2){
        JFrame frame = new JFrame("大图查看");
        frame.setLocationRelativeTo(null);
        frame.add(getChartPanel(materialsId, year, month1, month2));
        frame.setBounds(0, 0, Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height - 100);
        frame.setVisible(true);
	}
	
	/**
	 * 输出成图片
	 */
	public static boolean saveChartPNG(File target, String materialsId, String year, int month1, int month2) { 
		try {
			FileOutputStream fos = new FileOutputStream(target);
			JFreeChart chart = createChart(materialsId, year, month1, month2);
			//保存为图片 大小 1100 * 800
			ChartUtilities.saveChartAsPNG(target, chart, 1100, 800);
			fos.flush();
			fos.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
    public static void main(String[] args) {
    	ChartComponent timeSeriesChart = new ChartComponent();
    	ChartComponent.createChartFrame(null, null, 0, 0);
    }
}
