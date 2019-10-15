package com.galileoguzman.bookseeker;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.galileoguzman.bookseeker.models.Book;
import com.galileoguzman.bookseeker.utils.BaseActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddBookActivity extends BaseActivity {

    private EditText etName;
    private EditText etDescription;
    private EditText etLatitude;
    private EditText etLongitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        etName = (EditText) findViewById(R.id.etBookName);
        etDescription = (EditText) findViewById(R.id.etBookDescription);
        etLatitude = (EditText) findViewById(R.id.etBookLatitude);
        etLongitude = (EditText) findViewById(R.id.etBookLogitude);

    }

    public void btnAddBookPressed(View view) {

        showLoaderIndicator(getResources().getString(R.string.loading));

        doCreateBookRequest();
    }

    private void doCreateBookRequest() {
        String token = "Token " + dataProcessor.getStringValueForKey("userToken");
        Call<Book> response = apiService
                .doCreateNewBook(
                        token,
                        etName.getText().toString(),
                        etDescription.getText().toString(),
                        etLatitude.getText().toString(),
                        etLongitude.getText().toString()
                );

        response.enqueue(new Callback<Book>() {
            @Override
            public void onResponse(Call<Book> call, Response<Book> response) {

                if (response.code() == 201) {
                    Book book = response.body();

                    showResult(book);
                }

                Log.e("OnResponse", response.message());
                showErrorMessage();

            }

            @Override
            public void onFailure(Call<Book> call, Throwable t) {
                Log.e("OnFailure", t.getLocalizedMessage());
                closeLoaderIndicator();
                showErrorMessage();
            }
        });
    }

    private void showResult(Book book) {
        closeLoaderIndicator();
        showMessage(getResources().getString(R.string.AddBookCreatedMessage));
        finish();
    }

    private void showErrorMessage() {
        showMessage(getResources().getString(R.string.addBookError));
        finish();
    }
}
