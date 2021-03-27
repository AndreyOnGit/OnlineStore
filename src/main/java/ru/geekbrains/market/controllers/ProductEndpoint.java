package ru.geekbrains.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.geekbrains.market.services.ProductService;
import ru.geekbrains.market.ws.products.GetAllProductsRequest;
import ru.geekbrains.market.ws.products.GetAllProductsResponse;


@Endpoint
@RequiredArgsConstructor
public class ProductEndpoint {
    private static final String NAMESPACE_URI = "http://www.geekbrains.ru/market/ws/products";
    private final ProductService productService;

    /*
     * POST запрос на http://localhost:8189/market/ws/
     *
     * Headers : Content-Type : text/xml
     *
     * Body:
     *
     * <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:f="http://www.geekbrains.ru/market/ws/products">
            <soapenv:Header/>
            <soapenv:Body>
                <f:getAllProductsRequest/>
            </soapenv:Body>
        </soapenv:Envelope>
     *
     */

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllProductsRequest")
    @ResponsePayload
    public GetAllProductsResponse getAllProducts(@RequestPayload GetAllProductsRequest request) {
        GetAllProductsResponse response = new GetAllProductsResponse();
        productService.getAllProducts().forEach(response.getProducts()::add);
        return response;
    }
}
