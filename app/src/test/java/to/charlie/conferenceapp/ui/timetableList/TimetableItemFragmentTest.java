package to.charlie.conferenceapp.ui.timetableList;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TimetableItemFragmentTest
{

	@Test
	public void testGetDayOfMonthSuffixForRealDay()
	{
		TimetableItemFragment timetableItemFragment = new TimetableItemFragment();

		String suffix = timetableItemFragment.getDayOfMonthSuffix(1);
		assertEquals(suffix, "st");

		suffix = timetableItemFragment.getDayOfMonthSuffix(10);
		assertEquals(suffix, "th");

		suffix = timetableItemFragment.getDayOfMonthSuffix(2);
		assertEquals(suffix, "nd");

		suffix = timetableItemFragment.getDayOfMonthSuffix(23);
		assertEquals(suffix, "rd");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testGetDayOfMonthSuffixForNonDayOfMonth()
	{
		TimetableItemFragment timetableItemFragment = new TimetableItemFragment();
		timetableItemFragment.getDayOfMonthSuffix(600);
	}
}