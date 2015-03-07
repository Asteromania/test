package de.asteromania.doitlist.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import de.asteromania.doitlist.R;
import de.asteromania.doitlist.intent.IntentHandler;
import de.asteromania.doitlist.intent.IntentHandler.Intent;

public class DoItListMenuActivity extends AbstractDoItActivity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_menu);
	}

	public void createClicked(View view)
	{
		IntentHandler.startIntent(Intent.CREATE_LIST, this);
	}
	
	private void updateView()
	{
		ListView listView = (ListView) findViewById(R.id.lists_listview);
	}
}
