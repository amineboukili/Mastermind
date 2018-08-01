/*
 * (c) Copyright Ymagis S.A.
 *
 * This file is subject to the terms and conditions defined in
 * file 'LICENSE.txt', which is part of this source code package.
 */
package com.ymagis.exceptions;

/**
 * @author Team_4
 */
public class ScreenNotFoundExeption extends Exception {

    public ScreenNotFoundExeption() {
        super("The screen was not found");
    }

}
