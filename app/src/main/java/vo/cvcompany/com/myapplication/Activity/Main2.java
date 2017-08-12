package vo.cvcompany.com.myapplication.Activity;

import android.database.Cursor;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import vo.cvcompany.com.myapplication.MainActivity;
import vo.cvcompany.com.myapplication.Module.Description;
import vo.cvcompany.com.myapplication.Module.Myadapter;
import vo.cvcompany.com.myapplication.Module.Mysqlite;
import vo.cvcompany.com.myapplication.R;

public class Main2 extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    ArrayList<Description> arrayList;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        newArray();
        for(Description d: arrayList){
            Log.i(TAG, "onCreate: "+ d.getId()+" "+d.getDescription()+" "+d.getName());
        }
        showData();
    }

    public void showData(){

        Myadapter myadapter = new Myadapter(this, arrayList);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(myadapter);
    }

    public void  newArray(){
        arrayList = new ArrayList<Description>();
        Cursor cursor = Mysqlite.getMysqlite(this).getData();
        while(cursor.moveToNext()){
            if(cursor!=null && cursor.getCount()>0){
                arrayList.add(new Description(cursor.getString(0), cursor.getString(1), cursor.getString(2)));
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            NavUtils.navigateUpFromSameTask(this);
        }
        return super.onOptionsItemSelected(item);
    }
}
