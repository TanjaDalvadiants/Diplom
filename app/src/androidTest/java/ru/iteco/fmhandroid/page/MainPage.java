package ru.iteco.fmhandroid.page;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static ru.iteco.fmhandroid.test.TestUtils.waitDisplayed;

import androidx.test.espresso.ViewInteraction;

import ru.iteco.fmhandroid.R;

public class MainPage {
    ViewInteraction newsTitle = onView(withId(R.id.container_list_news_include_on_fragment_main));
    ViewInteraction missionButton = onView(withId(R.id.our_mission_image_button));

    ViewInteraction allNewsButton = onView(withId(R.id.all_news_text_view));

    public void validatePageLoaded() {
        newsTitle.check(matches(isDisplayed()));
        missionButton.check(matches(isDisplayed()));
        allNewsButton.check(matches(isDisplayed()));
    }

    public void waitUntilPageLoaded() {
        onView(isRoot()).perform(waitDisplayed(R.id.container_list_news_include_on_fragment_main, 10000));
    }

    public void goToMissionPage() {
        missionButton.perform(click());
    }


    public void goToAllNewsButton() {
        allNewsButton.perform(click());
    }

}
