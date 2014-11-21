/* 
 * Copyright (C) 2014 Bernardo Sulzbach
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.dungeon.core.game;

import org.joda.time.DateTime;

/**
 * Enumerated type of the parts of the day.
 * <p/>
 * Created by Bernardo Sulzbach on 24/09/2014.
 */
public enum PartOfDay {

    AFTERNOON("Afternoon", 0.8),
    DAWN("Dawn", 0.6),
    DUSK("Dusk", 0.6),
    EVENING("Evening", 0.4),
    MIDNIGHT("Midnight", 0.2),
    MORNING("Morning", 0.8),
    NIGHT("Night", 0.4),
    NOON("Noon", 1.0);

    private final String stringRepresentation;

    // How bright is the sun at this part of the day?
    // Should be bigger than or equal to 0 and smaller than or equal to 1.
    private double luminosity;

    PartOfDay(String stringRepresentation, double luminosity) {
        this.stringRepresentation = stringRepresentation;
        setLuminosity(luminosity);
    }

    public double getLuminosity() {
        return luminosity;
    }

    void setLuminosity(double luminosity) {
        if (luminosity < 0.0 || luminosity > 1.0) {
            throw new IllegalArgumentException("luminosity must be nonnegative and not bigger than 1.");
        }
        this.luminosity = luminosity;
    }

    /**
     * Returns the PartOfDay constant corresponding to a given time.
     *
     * @param dateTime a DateTime object.
     * @return a PartOfDay constant.
     */
    public static PartOfDay getCorrespondingConstant(DateTime dateTime) {
        int hour = dateTime.getHourOfDay();
        if (hour == 0 || hour == 23) {
            return MIDNIGHT;
        } else if (hour <= 4) {
            return NIGHT;
        } else if (hour <= 6) {
            return DAWN;
        } else if (hour <= 10) {
            return MORNING;
        } else if (hour <= 12) {
            return NOON;
        } else if (hour <= 16) {
            return AFTERNOON;
        } else if (hour <= 18) {
            return DUSK;
        } else if (hour <= 22) {
            return EVENING;
        }
        // This statement will never be reached.
        return null;
    }

    @Override
    public String toString() {
        return stringRepresentation;
    }

}
