/*
 * Copyright 2012 Decebal Suiu
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this work except in compliance with
 * the License. You may obtain a copy of the License in the LICENSE file, or at:
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.asf.wicket.dashboard.demo.chart;

import jofc2.model.Chart;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.model.Model;

import com.asf.wicket.dashboard.web.chart.OpenFlashChart;

/**
 * @author Decebal Suiu
 */
public class ChartDemoPage extends WebPage {

	private static final long serialVersionUID = 1L;

	public ChartDemoPage() {
		Chart chart1 = ChartDemoFactory.createDemoBarChart();
		add(new OpenFlashChart("chart1", "500", "300", new Model<String>(chart1.toString())));
		
		Chart chart2 = ChartDemoFactory.createDemoDoubleBarChart();
		add(new OpenFlashChart("chart2", "500", "300", new Model<String>(chart2.toString())));
		
		Chart chart3 = ChartDemoFactory.createDemoScatterChart();
		add(new OpenFlashChart("chart3", "500", "300", new Model<String>(chart3.toString())));
	}
	
}
