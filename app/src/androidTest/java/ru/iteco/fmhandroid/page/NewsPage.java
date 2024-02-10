package ru.iteco.fmhandroid.page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static ru.iteco.fmhandroid.test.TestUtils.waitDisplayed;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class NewsPage {
    ViewInteraction newsTitle = onView(withId(R.id.container_list_news_include_on_fragment_main));

    public void validatePageLoaded() {
        newsTitle.check(matches(isDisplayed()));
    }

    public void waitUntilPageLoaded() {
        onView(isRoot()).perform(waitDisplayed(R.id.container_list_news_include_on_fragment_main, 5000));

    }

}
