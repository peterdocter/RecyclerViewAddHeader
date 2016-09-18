package com.example.and2long.recyclerviewaddheader;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView mRecyclerView;
    private BaseRecyclerViewAdapter adapter;
    private LinearLayoutManager linearLayoutManager;
    private LinearLayoutManager gridLayoutManager;
    private ArrayList<String> data1;
    private ArrayList<String> data2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        linearLayoutManager = new LinearLayoutManager(this);
        gridLayoutManager = new GridLayoutManager(this, 2);

        View header = View.inflate(this, R.layout.header, null);
        Button btnSingle = (Button) header.findViewById(R.id.btn_single);
        Button btnDouble = (Button) header.findViewById(R.id.btn_double);
        Button btnData1 = (Button) header.findViewById(R.id.btn_data1);
        Button btnData2 = (Button) header.findViewById(R.id.btn_data2);
        btnSingle.setOnClickListener(this);
        btnDouble.setOnClickListener(this);
        btnData1.setOnClickListener(this);
        btnData2.setOnClickListener(this);


        mRecyclerView.setLayoutManager(gridLayoutManager);

        adapter = new BaseRecyclerViewAdapter();
        adapter.setHeaderView(header);
        adapter.addData(data1);
        adapter.setOnItemClickLitener(new BaseRecyclerViewAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Snackbar.make(mRecyclerView, data1.get(position - 1), Snackbar.LENGTH_SHORT).show();
            }
        });
        mRecyclerView.setAdapter(adapter);
    }

    private void initData() {
        data1 = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            data1.add(i + "");
        }
        data2 = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            data2.add(i + "...");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_single:
                mRecyclerView.setLayoutManager(linearLayoutManager);
                break;
            case R.id.btn_double:
                mRecyclerView.setLayoutManager(gridLayoutManager);
                break;
            case R.id.btn_data1:
                adapter.addData(data1);
                adapter.setOnItemClickLitener(new BaseRecyclerViewAdapter.OnItemClickLitener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Snackbar.make(mRecyclerView, data1.get(position - 1), Snackbar.LENGTH_SHORT).show();
                    }
                });
                adapter.notifyDataSetChanged();
                //mRecyclerView.setAdapter(adapter);
                break;
            case R.id.btn_data2:
                adapter.addData(data2);
                adapter.setOnItemClickLitener(new BaseRecyclerViewAdapter.OnItemClickLitener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Snackbar.make(mRecyclerView, data2.get(position - 1), Snackbar.LENGTH_SHORT).show();
                    }
                });
                adapter.notifyDataSetChanged();
                //mRecyclerView.setAdapter(adapter);
                break;
        }
    }
}
