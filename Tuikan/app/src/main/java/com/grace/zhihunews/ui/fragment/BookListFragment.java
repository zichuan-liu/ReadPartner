package com.grace.zhihunews.ui.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bartoszlipinski.recyclerviewheader.RecyclerViewHeader;
import com.grace.zhihunews.App;
import com.grace.zhihunews.PresenterCompl.BookListPresenterCompl;
import com.grace.zhihunews.R;
import com.grace.zhihunews.contract.BookListContact;
import com.grace.zhihunews.network.entity.Book;
import com.grace.zhihunews.network.entity.LoadBooks;
import com.grace.zhihunews.ui.activity.ExchangeActivity;
import com.grace.zhihunews.ui.activity.SearchActivity;
import com.grace.zhihunews.ui.adapter.BooksAdapter;
import com.grace.zhihunews.ui.base.BaseFragment;
import com.grace.zhihunews.ui.view.AddDialog;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * Created by Administrator on 2016/9/1.
 */
public class BookListFragment extends BaseFragment implements BookListContact.IBookListView {

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.toolbar_book)
    Toolbar toolbar;
    @BindView(R.id.toolbar_edit)
    Button toolbar_edit;
    @BindView(R.id.rv_read)
    RecyclerView rvReadList;
    @BindView(R.id.rv_header)
    RecyclerViewHeader rvHeader;
    @BindView(R.id.rv_time)
    TextView rvText;
    @BindView(R.id.qiandao)
    Button qiandao;
    @BindView(R.id.lvcheng)
    ImageButton lvcheng;

    private BookListContact.IBookListPresenter mBookListPresenter;
    private Unbinder unbinder;
    private List<Book> mBooks;
    private BooksAdapter booksAdapter;
    private Context mContext;
    private String filePath;

    @Override
    protected int getLayoutResId() {
        return R.layout.fragment_book_list;
    }

    @Override
    protected void initVariables() {
        mBooks = new ArrayList<>();
        booksAdapter = new BooksAdapter(getActivity(), mBooks);
        mBookListPresenter = new BookListPresenterCompl((App) getActivity().getApplicationContext(), this);
    }

    @Override
    protected void initViews(View view, Bundle savedInstanceState) {
        unbinder = ButterKnife.bind(this, view);
        mContext = getActivity();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        toolbar.setTitle("");
        toolbar_edit.setText(R.string.MainActivity_title_edit);
        toolbar.inflateMenu(R.menu.menu_main);

        rvReadList.setLayoutManager(linearLayoutManager);
        rvReadList.setAdapter(booksAdapter);
        rvReadList.addItemDecoration(new HorizontalDividerItemDecoration.Builder(getActivity())
                .colorResId(R.color.divider_grey)
                .size(getResources().getDimensionPixelSize(R.dimen.divider_height))
                .margin(getResources().getDimensionPixelSize(R.dimen.spacing_normal_high),
                        getResources().getDimensionPixelSize(R.dimen.spacing_normal_high))
                .build());

        rvHeader.attachTo(rvReadList, true);

//        rvReadList.addOnScrollListener(new EndlessRecyclerViewScrollListener(linearLayoutManager) {
//            @Override
//            public void onLoadMore(int page, int totalItemCount) {
//                String farthestDate;
//                farthestDate = dateList.get(dateList.size() - 1);
//                Log.d("farthestDate", farthestDate);
//                String previousDate = DateUtil.getPreviousDay(farthestDate);
//                Log.d("previousDate", previousDate);
//                dateList.add(previousDate);
//                for (int i = 0; i < dateList.size(); i++) {
//                    Log.d("dataList", i + dateList.get(i));
//                }
//                mBookListPresenter.loadBeforeBook(previousDate);
//            }
//        });
        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorAccent));
        mSwipeRefreshLayout.setOnRefreshListener(() -> {
            mSwipeRefreshLayout.setRefreshing(true);

            mBookListPresenter.refreshData();
            mBookListPresenter.loadBook();

            (new Handler()).postDelayed(() -> mSwipeRefreshLayout.setRefreshing(false), 1200);
        });

        /**
         * 测试阅读器
         */
        qiandao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qiandao.setText("已签到");
            }

        });
        lvcheng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, ExchangeActivity.class));
            }

        });

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_search:
                        startActivity(new Intent(mContext, SearchActivity.class));
                        break;
                    case R.id.action_add:
                        AddDialog addDialog = new AddDialog(mContext);

                        addDialog.setOnClickAddListener(
                                new OnClickAddListener() {

                                    /**
                                     * 点击确定按钮事件
                                     */
                                    @Override
                                    public void onPositiveClick() {
                                        onBookPositiveClick();
                                        filePath="";

                                        addDialog.dismiss();
                                    }

                                    /**
                                     * 点击取消按钮事件
                                     */
                                    @Override
                                    public void onNegtiveClick() {
                                        addDialog.dismiss();
                                    }

                                    @Override
                                    public void onPathClick() {
                                        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                                        intent.setType("*/*");//无类型限制
                                        intent.addCategory(Intent.CATEGORY_OPENABLE);
                                        startActivityForResult(intent, 1);
                                        addDialog.setBt_get_path(filePath);

                                    }

                                    @Override
                                    public void onImgClick() {

                                    }

                                    @Override
                                    public void onTitleClick() {

//                                        addDialog.setTitle("未知书籍");
                                    }

                                    @Override
                                    public void onWriteClick() {
//                                        addDialog.setMessage("未知作者");
                                    }
                                }

                        ).show();
                        break;
                    case R.id.toolbar_edit:
                        Toast.makeText(mContext, "暂未开启编辑功能", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });
    }

    /**
     * 导入添加的部分
     */
    private void onBookPositiveClick() {
        if(filePath==null||filePath==""){
            Toast.makeText(mContext, "请正确导入书籍", Toast.LENGTH_SHORT).show();
            return;
        }
        Book addBook= new Book();
        addBook.setTitle("未知书籍");
        addBook.setWriter("未知作者");
        addBook.setTxt_path(filePath);
        mBooks.add(addBook);
        booksAdapter.notifyDataSetChanged();
    }


    @Override
    protected void loadData() {
        mBookListPresenter.loadBook();
    }

    //BookListContact.INewsListView接口方法实现
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void showBook(LoadBooks loadBooks) {
        mBooks.clear();
        List<Book> books = loadBooks.getBooks();
        mBooks.addAll(books);
        booksAdapter.notifyDataSetChanged();
    }

    @Override
    public void showLoadFailureMsg(String errorMsg) {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            Uri uri = data.getData();
            if ("file".equalsIgnoreCase(uri.getScheme())){//使用第三方应用打开
                filePath = uri.getPath();
//                Toast.makeText(mContext,filePath+"11111",Toast.LENGTH_SHORT).show();
                return;
            }
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT) {//4.4以后
                filePath = getPath(mContext, uri);
//                Toast.makeText(mContext,filePath,Toast.LENGTH_SHORT).show();
            } else {//4.4以下下系统调用方法
                filePath = "";
                Toast.makeText(mContext, "安卓版本太低", Toast.LENGTH_SHORT).show();
            }
        }
    }

//    public String getRealPathFromURI(Uri contentUri) {
//        String res = null;
//        String[] proj = { MediaStore.Images.Media.DATA };
//        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
//        if(null!=cursor&&cursor.moveToFirst()){
//            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
//            res = cursor.getString(column_index);
//            cursor.close();
//        }
//        return res;
//    }

    /**
     * 专为Android4.4设计的从Uri获取文件绝对路径，以前的方法已不好使
     */
    @SuppressLint("NewApi")
    public String getPath(final Context context, final Uri uri) {

        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {

                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                return getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{split[1]};

                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {
            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }
        return null;
    }

    /**
     * Get the value of the data column for this Uri. This is useful for
     * MediaStore Uris, and other file-based ContentProviders.
     *
     * @param context       The context.
     * @param uri           The Uri to query.
     * @param selection     (Optional) Filter used in the query.
     * @param selectionArgs (Optional) Selection arguments used in the query.
     * @return The value of the _data column, which is typically a file path.
     */
    public String getDataColumn(Context context, Uri uri, String selection,
                                String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {column};

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int column_index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(column_index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    public boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    public boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    public boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }
}
