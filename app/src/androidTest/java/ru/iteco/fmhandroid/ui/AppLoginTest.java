package PACKAGE_NAME;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.pressImeActionButton;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withSubstring;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

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
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class AppLoginTest {

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    String LOGIN = "login2";
    String PASSWORD = "password2";
    String NEWS = "News";


    @Test
    public void appLoginTest() {
        try {
            Thread.sleep(6_000);
        } catch (InterruptedException exception) {

        }


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



        try {
            Thread.sleep(6_000);
        } catch (InterruptedException exception) {

        }

        ViewInteraction newsTitle = onView(withId(R.id.container_list_news_include_on_fragment_main));
        newsTitle.check(matches(isDisplayed()));

        ViewInteraction userButton = onView(withId(R.id.authorization_image_button));
        userButton.check(matches(isDisplayed()));
        userButton.perform(click());

        ViewInteraction logOutButton = onView(withText("Log out"));
        logOutButton.check(matches(isDisplayed()));
        logOutButton.perform(click());

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
