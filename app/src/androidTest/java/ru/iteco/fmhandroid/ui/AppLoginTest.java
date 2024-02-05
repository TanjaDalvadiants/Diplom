package ru.iteco.fmhandroid.ui;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;
import static ru.iteco.fmhandroid.ui.TestUtils.logOut;
import static ru.iteco.fmhandroid.ui.TestUtils.waitDisplayed;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.EditText;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.AfterClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ru.iteco.fmhandroid.R;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AppLoginTest {

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    String LOGIN = "login2";
    String PASSWORD = "password2";
    String NEWS = "News";




    @AfterClass
    public static void logout() {
        logOut();
    }





    @Test
    public void appLoginTest() {

        onView(isRoot()).perform(waitDisplayed(R.id.login_text_input_layout, 6000));

        ViewInteraction loginInputText = onView(
                allOf(
                        isDescendantOfA(withId(R.id.login_text_input_layout)), // Это ID вашего TextInputLayout
                        isAssignableFrom(EditText.class) // Указывает, что мы ищем EditText
                )
        );
        loginInputText.check(matches(isDisplayed()));
        loginInputText.perform(replaceText(LOGIN), closeSoftKeyboard());

        ViewInteraction passwordInputText = onView(
                allOf(
                        isDescendantOfA(withId(R.id.password_text_input_layout)), // Это ID вашего TextInputLayout
                        isAssignableFrom(EditText.class) // Указывает, что мы ищем EditText
                )
        );
        passwordInputText.check(matches(isDisplayed()));
        passwordInputText.perform(replaceText(PASSWORD), closeSoftKeyboard());

        ViewInteraction signInButton = onView(withId(R.id.enter_button));
        signInButton.check(matches(isDisplayed()));
        signInButton.perform(click());

        onView(isRoot()).perform(waitDisplayed(R.id.container_list_news_include_on_fragment_main, 5000));

        ViewInteraction newsTitle = onView(withId(R.id.container_list_news_include_on_fragment_main));
        newsTitle.check(matches(isDisplayed()));



    }


    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
