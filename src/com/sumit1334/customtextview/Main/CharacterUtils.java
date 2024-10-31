package com.sumit1334.customtextview.Main;



import java.util.*;

public class CharacterUtils {
    public CharacterUtils() {
    }

    public static Collection<? extends com.sumit1334.customtextview.Main.CharacterDiffResult> diff(CharSequence oldText, CharSequence newText) {
        List<CharacterDiffResult> differentList = new ArrayList();
        Set<Integer> skip = new HashSet();

        for(int i = 0; i < oldText.length(); ++i) {
            char c = oldText.charAt(i);

            for(int j = 0; j < newText.length(); ++j) {
                if (!skip.contains(j) && c == newText.charAt(j)) {
                    skip.add(j);
                    CharacterDiffResult different = new CharacterDiffResult();
                    different.c = c;
                    different.fromIndex = i;
                    different.moveIndex = j;
                    differentList.add(different);
                    break;
                }
            }
        }

        return differentList;
    }

    public static int needMove(int index, List<CharacterDiffResult> differentList) {
        Iterator var2 = differentList.iterator();

        CharacterDiffResult different;
        do {
            if (!var2.hasNext()) {
                return -1;
            }

            different = (CharacterDiffResult)var2.next();
        } while(different.fromIndex != index);

        return different.moveIndex;
    }

    public static boolean stayHere(int index, List<CharacterDiffResult> differentList) {
        Iterator var2 = differentList.iterator();

        CharacterDiffResult different;
        do {
            if (!var2.hasNext()) {
                return false;
            }

            different = (CharacterDiffResult)var2.next();
        } while(different.moveIndex != index);

        return true;
    }

    public static float getOffset(int from, int move, float progress, float startX, float oldStartX, List<Float> gaps, List<Float> oldGaps) {
        float dist = startX;

        for(int i = 0; i < move; ++i) {
            dist += (Float)gaps.get(i);
        }

        float cur = oldStartX;

        for(int i = 0; i < from; ++i) {
            cur += (Float)oldGaps.get(i);
        }

        return cur + (dist - cur) * progress;
    }
}

