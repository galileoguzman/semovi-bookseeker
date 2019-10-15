package com.galileoguzman.bookseeker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.galileoguzman.bookseeker.adapters.BooksAdapter;
import com.galileoguzman.bookseeker.models.AllBooks;
import com.galileoguzman.bookseeker.models.Book;
import com.galileoguzman.bookseeker.services.APIService;
import com.galileoguzman.bookseeker.utils.ApiUtils;
import com.galileoguzman.bookseeker.utils.BaseActivity;
import com.galileoguzman.bookseeker.utils.DataProcessor;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity {

    private APIService apiService;
    private DataProcessor dataProcessor;
    private ListView lvBooks;
    private BooksAdapter booksAdapter;

    private ArrayList<Book> books;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dataProcessor = new DataProcessor(this);

        lvBooks = (ListView) findViewById(R.id.lvBooks);


        apiService = ApiUtils.getAPIService();
        getBooks();

    }

    public void btnLogoutPressed(View view) {
        dataProcessor.saveStringValueForKey("userToken", "---");
        dataProcessor.saveBooleanValueForKey("isActiveSession", false);

        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    public void getBooks() {

        showLoaderIndicator(getResources().getString(R.string.loading));

        String token = "Token " + dataProcessor.getStringValueForKey("userToken");
        Call<List<Book>> response = apiService.doGetAllBooks(token);

        response.enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                List<Book> allBooks = response.body();

                showResults(allBooks);

            }

            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {

            }
        });
    }

    public void showResults(List<Book> result) {
        books = (ArrayList<Book>) result;
        booksAdapter = new BooksAdapter(this, (ArrayList<Book>) books);
        lvBooks.setAdapter(booksAdapter);

        closeLoaderIndicator();
    }
}
