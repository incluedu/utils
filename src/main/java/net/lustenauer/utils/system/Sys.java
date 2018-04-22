/*
 * MIT License
 *
 * Copyright (c) 2017 - 2018 Patric Hollenstein
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package net.lustenauer.utils.system;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Sys {

    /**
     * Returns the system uptime but the problem is work only on english language system, must try to find a better
     * solution
     *
     * @return System uptime in milliseconds or -1
     * @throws Exception any exception possible during process
     * @since Version 1.3.0
     */
    public static long getSystemUptime() throws Exception {
        long uptime = -1;
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            Process uptimeProc = Runtime.getRuntime().exec("net stats srv");
            BufferedReader in = new BufferedReader(new InputStreamReader(uptimeProc.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                if (line.startsWith("Statistics since")) {
                    SimpleDateFormat format = new SimpleDateFormat("'Statistics since' MM/dd/yyyy hh:mm:ss a");
                    Date boottime = format.parse(line);
                    uptime = System.currentTimeMillis() - boottime.getTime();
                    break;
                }
            }
        } else if (os.contains("mac") || os.contains("nix") || os.contains("nux") || os.contains("aix")) {
            Process uptimeProc = Runtime.getRuntime().exec("uptime");
            BufferedReader in = new BufferedReader(new InputStreamReader(uptimeProc.getInputStream()));
            String line = in.readLine();
            if (line != null) {
                Pattern parse = Pattern.compile("((\\d+) days,)? (\\d+):(\\d+)");
                Matcher matcher = parse.matcher(line);
                if (matcher.find()) {
                    String _days = matcher.group(2);
                    String _hours = matcher.group(3);
                    String _minutes = matcher.group(4);
                    int days = _days != null ? Integer.parseInt(_days) : 0;
                    int hours = _hours != null ? Integer.parseInt(_hours) : 0;
                    int minutes = _minutes != null ? Integer.parseInt(_minutes) : 0;
                    uptime = TimeUnit.MILLISECONDS.convert(days, TimeUnit.DAYS) +
                            TimeUnit.MILLISECONDS.convert(hours, TimeUnit.HOURS) +
                            TimeUnit.MILLISECONDS.convert(minutes, TimeUnit.MINUTES);
                }
            }
        }
        return uptime;
    }
}
