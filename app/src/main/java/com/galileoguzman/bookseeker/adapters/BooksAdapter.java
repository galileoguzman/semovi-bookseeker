package com.galileoguzman.bookseeker.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.galileoguzman.bookseeker.R;
import com.galileoguzman.bookseeker.models.Book;

import java.util.ArrayList;
import java.util.List;

public class BooksAdapter extends ArrayAdapter<Book> {

    private Context mContext;
    private List<Book> userList = new ArrayList<>();

    public BooksAdapter(@NonNull Context context,
                        ArrayList<Book> list) {
        super(context, 0, list);

        mContext = context;
        userList = list;
    }

    @NonNull
    @Override
    public View getView(int position,
                        @Nullable View convertView,
                        @NonNull ViewGroup parent) {

        View listItem = convertView;

        if (listItem == null) {
            listItem = LayoutInflater
                    .from(mContext)
                    .inflate(R.layout.books_item, parent, false);
        }

        Book book = userList.get(position);

        TextView tvUserFullname = (TextView) listItem
                .findViewById(R.id.tvBookName);

        tvUserFullname.setText(book.getName());

        return listItem;

    }

}
