package ru.geekbrains.market;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:secured.properties")
public class MarketApplication {

    /*
     * 1. Хотел исправить недостаток с работой корзины:
     * непустая корзина переходить от одног юзера к другому (такой же недостаток в happy-market).
     * Создание прототипа @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE) проблему не решает, а усугублят:
     * перестаёт очищаться корзина просле формирования заказа.
     * Предполагаю, что если создать бин корзины,привязанный только к пользователю, решит проблему, но тогда
     * её надо будет инжектить в остальные сервисы не через поле final.
     * Хотелось бы решить эту задачу на вебинаре.
     * 2. Форму (<form>) нельзя размещать в таблице (<table>), это невалидно, хотя получить id заказа проще
     * именно через "ng-repeat="o in Orders"".
     * Хотелось бы увидеть более корректные решения задачи с введением адреса.
     * 3. Прокомментируйте @OneToMany(mappedBy = "order") из class Order. Что такое mappedBy? Чем отличаеся
     * от использования  @JoinColumn?
     */

    public static void main(String[] args) {
        SpringApplication.run(MarketApplication.class, args);
    }
}
