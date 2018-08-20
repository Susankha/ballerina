/*
 *  Copyright (c) 2018, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *  WSO2 Inc. licenses this file to you under the Apache License,
 *  Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package org.ballerinalang.test.service.http.configuration;

import org.ballerinalang.test.util.BaseTest;
import org.ballerinalang.test.util.HttpClientRequest;
import org.ballerinalang.test.util.HttpResponse;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Testing accept-encoding header.
 */
@Test(groups = "http-test")
public class AcceptEncodingHeaderTestCase extends BaseTest {

    private static final String CONTENT_TYPE = "Content-Type";
    private static final String ACCEPT_VALUE = "AcceptValue";
    private Map<String, String> headers = new HashMap<>();
    private final int servicePort = 9091;

    @Test(description = "Tests the behaviour when Accept Encoding option is enable.")
    public void testAcceptEncodingEnabled() throws IOException {
        String expectedResponse = "{\"acceptEncoding\":\"deflate, gzip\"}";
        String message = "accept encoding test";
        headers.put(ACCEPT_VALUE, "enable");
        headers.put(CONTENT_TYPE, "text/plain");
        HttpResponse response = HttpClientRequest.doPost(serverInstance.getServiceURLHttp(servicePort, "passthrough"),
                message, headers);
        Assert.assertEquals(response.getResponseCode(), 200, "Response code mismatched");

        Assert.assertEquals(response.getData(), expectedResponse,
                "Response does not contains accept-encoding value.");
    }

    @Test(description = "Tests the behaviour when Accept Encoding option is disable.")
    public void testAcceptEncodingDisabled() throws IOException {
        String expectedResponse = "{\"acceptEncoding\":\"Accept-Encoding hdeaer not present.\"}";
        String message = "accept encoding test";
        headers.put(ACCEPT_VALUE, "disable");
        headers.put(CONTENT_TYPE, "text/plain");
        HttpResponse response = HttpClientRequest.doPost(serverInstance.getServiceURLHttp(servicePort, "passthrough"),
                message, headers);
        Assert.assertEquals(response.getResponseCode(), 200, "Response code mismatched");

        Assert.assertEquals(response.getData(), expectedResponse,
                "Response does not contains accept-encoding value.");
    }

    @Test(description = "Tests the behaviour when Accept Encoding option is auto.")
    public void testAcceptEncodingAuto() throws IOException {
        String expectedResponse = "{\"acceptEncoding\":\"Accept-Encoding hdeaer not present.\"}";
        String message = "accept encoding test";
        headers.put(ACCEPT_VALUE, "auto");
        headers.put(CONTENT_TYPE, "text/plain");
        HttpResponse response = HttpClientRequest.doPost(serverInstance.getServiceURLHttp(servicePort, "passthrough"),
                message, headers);
        Assert.assertEquals(response.getResponseCode(), 200, "Response code mismatched");

        Assert.assertEquals(response.getData(), expectedResponse,
                "Response does not contains accept-encoding value.");
    }
}
