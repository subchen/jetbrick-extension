/**
 * Copyright 2013-2014 Guoqiang Chen, Shanghai, China. All rights reserved.
 *
 * Email: subchen@gmail.com
 * URL: http://subchen.github.io/
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package jetbrick.pinyin;

import org.junit.Assert;
import org.junit.Test;

public class ChinesePinyinTestCase {
    private static final ChinesePinyin instance = ChinesePinyin.getInstance();
    
    @Test
    public void testGetChinese() {
        Assert.assertEquals("上海浦东鍀", instance.getChinese("001上海の浦东♂abc鍀"));
    }
    
    @Test
    public void testGetFirstPinyin() {
        Assert.assertEquals("001shのpd", instance.getFirstPinyin("001上海の浦东"));
        Assert.assertEquals("zhrmghg", instance.getFirstPinyin("中华人民共和国"));
    }
    
    @Test
    public void testGetFullPinyin() {
        Assert.assertEquals("ceshi", instance.getFullPinyin("测试"));
        Assert.assertEquals("shanghaiのceshi", instance.getFullPinyin("上海の测试"));
    }

}
