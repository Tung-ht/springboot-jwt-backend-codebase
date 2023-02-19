package com.sapo.mock.techshop.common.utils;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public class DataUtils {
    private DataUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static Pageable mapPageDtoToPageable(Integer page, Integer size) {
        if (page != null) {
            if (page < 1) page = 0;
        } else {
            page = 0;
        }
        if (size != null) {
            if (size <= 0) size = 1;
        } else {
            size = 10;
        }
        return PageRequest.of(page, size);
    }

    public static Integer safeToInteger(Object obj) {
        return safeToInteger(obj, 0);
    }

    public static Integer safeToInteger(Object obj, Integer defaultValue) {
        if (obj == null) {
            return defaultValue;
        }
        if (obj instanceof Integer) {
            return (Integer) obj;
        }
        try {
            return Integer.valueOf(obj.toString());
        } catch (Exception ex) {
            return defaultValue;
        }
    }

    public static Double safeToDouble(Object obj) {
        return safeToDouble(obj, 0d);
    }

    public static Double safeToDouble(Object obj, Double defaultValue) {
        if (obj == null) {
            return defaultValue;
        }
        if (obj instanceof Double) {
            return (Double) obj;
        }
        try {
            return Double.valueOf(obj.toString());
        } catch (Exception ex) {
            return defaultValue;
        }
    }

    public static Long safeToLong(Object obj) {
        return safeToLong(obj, 0L);
    }

    public static Long safeToLong(Object obj, Long defaultValue) {
        if (obj == null) {
            return defaultValue;
        }
        if (obj instanceof Long) {
            return (Long) obj;
        }
        try {
            return Long.valueOf(obj.toString());
        } catch (Exception ex) {
            return defaultValue;
        }
    }

    public static String safeToString(Object obj) {
        return safeToString(obj, null);
    }

    public static String safeToString(Object obj, String defaultValue) {
        if (obj == null) {
            return defaultValue;
        }
        if (obj instanceof String) {
            return (String) obj;
        }
        return defaultValue;
    }

    public static LocalDateTime safeToLocalDateTime(Object obj) {
        return safeToLocalDateTime(obj, null);
    }

    public static LocalDateTime safeToLocalDateTime(Object obj, LocalDateTime defaultValue) {
        if (obj == null) {
            return defaultValue;
        }
        if (obj instanceof Timestamp) {
            try {
                return ((Timestamp) obj).toLocalDateTime();
            } catch (Exception ex) {
                return defaultValue;
            }
        }
        return defaultValue;
    }

    public static boolean isNullOrEmpty(String str) {
        return (str == null || str.trim().isEmpty());
    }

    public static boolean isNull(Object obj) {
        return obj == null;
    }


}
