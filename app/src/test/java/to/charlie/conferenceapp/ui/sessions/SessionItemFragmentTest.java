package to.charlie.conferenceapp.ui.sessions;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SessionItemFragmentTest
{

	@Test
	public void testGetDayOfMonthSuffixForRealDay()
	{
		SessionItemFragment sessionItemFragment = new SessionItemFragment();

		String suffix = sessionItemFragment.getDayOfMonthSuffix(1);
		assertEquals(suffix, "st");

		suffix = sessionItemFragment.getDayOfMonthSuffix(10);
		assertEquals(suffix, "th");

		suffix = sessionItemFragment.getDayOfMonthSuffix(2);
		assertEquals(suffix, "nd");

		suffix = sessionItemFragment.getDayOfMonthSuffix(23);
		assertEquals(suffix, "rd");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetDayOfMonthSuffixForNonDayOfMonth()
	{
		SessionItemFragment sessionItemFragment = new SessionItemFragment();
		sessionItemFragment.getDayOfMonthSuffix(600);
	}
}