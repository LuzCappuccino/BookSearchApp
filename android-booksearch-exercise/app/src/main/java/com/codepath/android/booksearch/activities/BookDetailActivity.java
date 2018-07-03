package com.codepath.android.booksearch.activities;

import android.os.Bundle;
import android.os.Parcel;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

import com.codepath.android.booksearch.GlideApp;
import com.codepath.android.booksearch.R;
import com.codepath.android.booksearch.models.Book;
import com.loopj.android.http.AsyncHttpClient;

import org.parceler.Parcels;

public class BookDetailActivity extends AppCompatActivity {
    private ImageView ivBookCover;
    private TextView tvTitle;
    private TextView tvAuthor;
    private AsyncHttpClient client;

    public Book myBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);
        // Fetch views
        ivBookCover = (ImageView) findViewById(R.id.ivBookCover);
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvAuthor = (TextView) findViewById(R.id.tvAuthor);

        // Extract book object from intent extras
        myBook = (Book) Parcels.unwrap(getIntent().getParcelableExtra(Book.class.getSimpleName()));
        // Use book object to populate data into views
        tvTitle.setText(myBook.getTitle());
        tvAuthor.setText(myBook.getAuthor());

        int radius = 30; // corner radius, higher value = more rounded
        int margin = 10; // crop margin, set to 0 for corners with no crop

        GlideApp.with(getApplicationContext())
                .load(myBook.getCoverUrl())
                .into(ivBookCover);

//        ActionBar actionBar = getSupportActionBar();
//        getSupportActionBar().setTitle(myBook.getTitle());
//        String title = actionBar.getTitle().toString(); // ???? why do we need this???
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_book_detail, menu);
        MenuItem searchItem = menu.findItem(R.id.BookSearch);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
