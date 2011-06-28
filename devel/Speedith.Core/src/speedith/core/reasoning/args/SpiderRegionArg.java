/*
 *   Project: Speedith.Core
 * 
 * File name: SpiderRegionArg.java
 *    Author: Matej Urbas [matej.urbas@gmail.com]
 * 
 *  Copyright © 2011 Matej Urbas
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package speedith.core.reasoning.args;

import speedith.core.lang.Region;

/**
 * This inference rule argument provides the following (in addition to data
 * provided by {@link SpiderArg}):
 *  <ul>
 *      <li>a sub-{@link Region region} of the {@link
 *          SpiderRegionArg#getSpider() spider's} habitat.</li>
 *  </ul>
 * <p>This argument type is used to target a particular sub-region of the
 * spider's habitat.</p>
 * <p><span style="font-weight:bold">Note</span>: there is no way for this
 * object to check whether the given region actually is a sub-region of the
 * given spider. The object has no knowledge of the spider's habitat.</p>
 * @author Matej Urbas [matej.urbas@gmail.com]
 */
public class SpiderRegionArg extends SpiderArg {

    // <editor-fold defaultstate="collapsed" desc="Fields">
    private Region region;
    // </editor-fold>

    public SpiderRegionArg(int subgoalIndex, int primarySDIndex, String spider, Region region) {
        super(subgoalIndex, primarySDIndex, spider);
        this.region = region;
    }

    public Region getRegion() {
        return region;
    }
}
