package ua.volyn.vcolnuft;

public record ApiResponse(String fact, int length) {}
// Record автоматично генерує:
// конструктор,
// геттери (fact(), length())
// equals(), hashCode(), toString()