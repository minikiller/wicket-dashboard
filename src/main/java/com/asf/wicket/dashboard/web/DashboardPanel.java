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
package com.asf.wicket.dashboard.web;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.markup.html.IHeaderResponse;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.request.resource.PackageResourceReference;

import com.asf.wicket.dashboard.Dashboard;
import com.asf.wicket.dashboard.DashboardColumn;

/**
 * @author Decebal Suiu
 */
public class DashboardPanel extends GenericPanel<Dashboard> {
	
	private static final long serialVersionUID = 1L;

	private List<DashboardColumnPanel> columnPanels;
	
	public DashboardPanel(String id, IModel<Dashboard> model) {
		super(id, model);			

		addColumnsPanel();
	}
	
	@Override
	public void renderHead(IHeaderResponse response) {
		super.renderHead(response);
		
		response.renderJavaScriptReference("js/jquery.json-2.2.min.js");
        response.renderJavaScriptReference(new PackageResourceReference(DashboardPanel.class, "dashboard.js"));        
        response.renderCSSReference(new PackageResourceReference(DashboardPanel.class, "dashboard.css"));
        
	}
	
	public Dashboard getDashboard() {
		return getModelObject();
	}
	
	private void addColumnsPanel() {
		final IModel<List<DashboardColumn>> columnsModel = new LoadableDetachableModel<List<DashboardColumn>>() {

			private static final long serialVersionUID = 1L;

			@Override
			protected List<DashboardColumn> load() {
				List<DashboardColumn> dashboardColumns = new ArrayList<DashboardColumn>();
			    int columnCount = getDashboard().getColumnCount();
			    for (int i = 0; i < columnCount; i++) {
			    	dashboardColumns.add(new DashboardColumnModel(i).getObject());
			    }
			    
			    return dashboardColumns;
			}
			
		};
		ListView<DashboardColumn> columnsView = new ListView<DashboardColumn>("columns", columnsModel) {

			private static final long serialVersionUID = 1L;

			@Override
			protected void onBeforeRender() {
				if (!hasBeenRendered()) {
					columnPanels = new ArrayList<DashboardColumnPanel>();
				}
				
				super.onBeforeRender();
			}

			@Override
			protected void populateItem(ListItem<DashboardColumn> item) {
				// TODO
			    float columnPanelWidth = 100 / columnsModel.getObject().size();
		    	DashboardColumnPanel columnPanel = createColumnPanel("column", item.getIndex());
		    	columnPanel.setRenderBodyOnly(true);
		    	columnPanel.getColumnContainer().add(AttributeModifier.replace("style", "width: " + columnPanelWidth + "%;"));		    	
		    	item.add(columnPanel);
		    	
		    	columnPanels.add(columnPanel);
			}
			
		};
		add(columnsView);
	}

	private DashboardColumnPanel createColumnPanel(String id, int index) {
		return new DashboardColumnPanel(id, new DashboardColumnModel(index));
	}
	
}
