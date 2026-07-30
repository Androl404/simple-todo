from PIL import Image, ImageDraw

def make_icon(size):
    img = Image.new("RGBA", (size, size), (0, 0, 0, 0))
    d = ImageDraw.Draw(img)
    s = size / 48.0  # scale factor so coordinates below are for a 48x48 base

    def pt(x, y):
        return (x * s, y * s)

    def rect(x0, y0, x1, y1, **kw):
        d.rectangle([pt(x0, y0), pt(x1, y1)], **kw)

    def rrect(x0, y0, x1, y1, radius, **kw):
        d.rounded_rectangle([pt(x0, y0), pt(x1, y1)], radius=radius * s, **kw)

    # Clipboard body
    rrect(6, 4, 42, 44, radius=4, fill=(245, 247, 250, 255), outline=(70, 90, 110, 255), width=max(1, round(2 * s)))

    # Clip at top
    rrect(17, 1, 31, 8, radius=2, fill=(120, 140, 160, 255))

    # Checklist rows: checkbox + line, 3 rows
    row_ys = [15, 24, 33]
    checked = [True, True, False]
    accent = (46, 160, 110, 255)   # green for checked
    empty = (200, 206, 214, 255)   # grey outline for unchecked
    line_color = (150, 160, 172, 255)

    for y, is_checked in zip(row_ys, checked):
        box_size = 6
        x0, y0 = 10, y
        x1, y1 = x0 + box_size, y0 + box_size
        if is_checked:
            rrect(x0, y0, x1, y1, radius=1.5, fill=accent)
            # checkmark
            d.line([pt(x0 + 1.2, y0 + 3.2), pt(x0 + 2.6, y0 + 5), pt(x1 - 0.8, y0 + 1)],
                   fill=(255, 255, 255, 255), width=max(1, round(1.4 * s)), joint="curve")
        else:
            rrect(x0, y0, x1, y1, radius=1.5, outline=empty, width=max(1, round(1.2 * s)))

        # text line next to checkbox
        rrect(20, y0 + 1.5, 36, y0 + 3.5, radius=1, fill=line_color)

    return img

for size in (16, 32, 48, 64, 128, 256, 512, 1024):
    make_icon(size).save(f"/tmp/icon{size}.png")

print("done")
